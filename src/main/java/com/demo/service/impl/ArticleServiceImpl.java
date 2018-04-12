package com.demo.service.impl;

import com.demo.dao.ArticleDao;
import com.demo.domain.Article;
import com.demo.service.ArticleService;
import com.demo.service.RedisService;

import static com.demo.utils.ConstantsUtils.*;

import com.demo.utils.MarkDownUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 文章业务处理类
 *
 * @author Mr.Deng
 * Created on 2018/3/27 13:52
 **/
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    /**
     * 每页获取的条数
     **/
    private static final int PAGE_SIZE = 10;

    @Autowired
    ArticleDao articleDao;

    @Autowired
    RedisService redisService;

    /**
     * 分页查询Article
     **/
    @Override
    @SuppressWarnings("unchecked")
    public PageInfo findArticlePage(String tags, int page) {

        //只缓存第一页数据
        if (redisService.haskey(REDIS_ARTICLE_KEY + "page_" + page) && page == 1
                && StringUtils.isEmpty(tags)) {
            return (PageInfo<Article>) redisService.get(REDIS_ARTICLE_KEY + "page_" + page);
        }

        //查询参数
        Map paramMap = new HashMap();
        if (!StringUtils.isEmpty(tags)) {
            paramMap.put("tags", tags);
        }

        paramMap.put("page", page);

        // startPage(第几页, 多少条数据)
        PageHelper.startPage(page, PAGE_SIZE);
        // Mybatis查询方法
        List<Article> list = articleDao.findArticle(paramMap);
        // 用PageInfo对结果进行包装
        PageInfo articlePage = new PageInfo(list);

        articlePage = setPreview(articlePage);

        if (redisService.haskey(REDIS_ARTICLE_KEY + "page_" + page) && page == 1
                && StringUtils.isEmpty(tags)) {
            redisService.set(REDIS_ARTICLE_KEY + "page_" + page, articlePage,
                    REDIS_ARTICLE_TIME, TimeUnit.SECONDS);
        }
        return articlePage;
    }

    /**
     * 查询单条数据
     * Redis更新：先看缓存是否存在，不存在才去数据库获取，同时保存到缓存中
     * Created on 2018/3/30 19:57
     **/
    @Override
    public Article findById(int articleId) {

        if (redisService.haskey(REDIS_ARTICLE_KEY + articleId)) {
            return (Article) redisService.get(REDIS_ARTICLE_KEY + articleId);
        }

        Article article = articleDao.selectByPrimaryKey(articleId);
        //md to html
        article.setContent(MarkDownUtils.mdToHtml(article.getContent()));
        //存入redis
        redisService.set(REDIS_ARTICLE_KEY + articleId, article,
                REDIS_ARTICLE_TIME, TimeUnit.SECONDS);

        //更新浏览数量
        updateArticleViewNum(articleId);

        return article;
    }

    /**
     * 保存文章
     * 同时刷新redis缓存
     * Created on 2018/3/28 10:00
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Article save(Article article) {
        article.setAuthor("dengym");
        article.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        article.setViewNum(0);
        articleDao.insert(article);

        // 刷新redis缓存
        redisService.set(REDIS_ARTICLE_KEY + article.getArticleId(), article,
                REDIS_ARTICLE_TIME, TimeUnit.SECONDS);

        return null;
    }

    /**
     * 更新浏览数量
     *
     * @param articleId 主键
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticleViewNum(int articleId) {
        articleDao.updateViewNum(articleId);
    }


    public PageInfo setPreview(PageInfo pageInfo) {
        for (Article article : (Iterable<Article>) pageInfo.getList()) {
            article.setPreview(artcileSubStr(article.getContent(), PREVIEW_SUBSTR_SIZE));
        }
        return pageInfo;
    }

    /**
     * 截取文章，显示预览
     *
     * @param content 文章内容
     * @param length  要截取文字的个数
     *                Created on 2018/3/28 10:32
     **/
    public String artcileSubStr(String content, int length) {
        String txt = MarkDownUtils.mdToText(content);
        if (txt.length() < length) {
            length = txt.length();
        }
        //转换成TXT
        return txt.substring(0, length);
    }
}