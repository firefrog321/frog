package com.demo.service.impl;

import com.demo.dao.ArticleDao;
import com.demo.domain.Article;
import com.demo.service.ArticleService;
import com.demo.service.RedisService;
import com.demo.utils.ConstantsUtils;
import com.demo.utils.MarkDownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Path;
import javax.transaction.Transactional;
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

    private static final int PAGE_SIZE = 10; //每页获取的条数

    @Autowired
    ArticleDao articleDao;

    @Autowired
    RedisService redisService;

    /**
     * 分页查询Article
     **/
    @SuppressWarnings("unchecked")
    public Page<Article> findArticlePage(int page) {

        if (redisService.haskey(ConstantsUtils.REDIS_ARTICLE_KEY + "page_" + page) && page == 0) {
            //List<Page<Article>> redisList= (List)redisService.get(ConstantsUtils.REDIS_ARTICLE_KEY + "page_" + page);
            //return redisList.get(0);
            //System.out.print("redis 执行了一次");
            return (Page<Article>) redisService.get(ConstantsUtils.REDIS_ARTICLE_KEY + "page_" + page);
        }

        //指定pageable对象 从0开始 先根据create_date字段desc排序，再articleId排序
        String[] sortColummn = {"createDate", "articleId"};
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, new Sort(Sort.Direction.DESC, sortColummn));

        Page<Article> articlePage = articleDao.findAll(pageable);

        //存入redis
        //List<Page<Article>> list = new ArrayList<Page<Article>>();
        //list.add(articlePage);
        redisService.set(ConstantsUtils.REDIS_ARTICLE_KEY + "page_" + page, articlePage,
                ConstantsUtils.REDIS_ARTICLE_TIME, TimeUnit.SECONDS);

        return articlePage;
    }

    /**
     * 查询单条数据
     * Redis更新：先看缓存是否存在，不存在才去数据库获取，同时保存到缓存中
     * Created on 2018/3/30 19:57
     **/
    @Override
    public Article findById(int articleId) {

        if (redisService.haskey(ConstantsUtils.REDIS_ARTICLE_KEY + articleId)) {
            return (Article) redisService.get(ConstantsUtils.REDIS_ARTICLE_KEY + articleId);
        }

        Article article = articleDao.findById(articleId);
        //md to html
        article.setContent(MarkDownUtils.mdToHtml(article.getContent()));
        //存入redis
        redisService.set(ConstantsUtils.REDIS_ARTICLE_KEY + articleId, article,
                ConstantsUtils.REDIS_ARTICLE_TIME, TimeUnit.SECONDS);

        return article;
    }

    /**
     * 保存文章
     * 同时刷新redis缓存
     * Created on 2018/3/28 10:00
     **/
    @Override
    @Transactional
    public Article save(Article article) {
        article.setAuthor("dengym");
        article.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        System.out.print(article.getContent());
        articleDao.save(article);

        //刷新redis缓存
        redisService.set(ConstantsUtils.REDIS_ARTICLE_KEY + article.getArticleId(), article,
                ConstantsUtils.REDIS_ARTICLE_TIME, TimeUnit.SECONDS);

        return null;
    }

    //更新浏览数量
    @Override
    @Transactional
    public void updateArticleViewNum(int articleId) {
        articleDao.updateViewNum(articleId);
    }


    /**
     * 根据tags查询文章，只支持单个tags
     * tags没有就根据page查询文章
     * Created on 2018/4/2 10:06
     **/
    public Page<Article> getArticleByTags(String tags, int page) {

        if (StringUtils.isEmpty(tags)) {
            return findArticlePage(page);
        }
        //指定pageable对象 从0开始 先根据create_date字段desc排序，再articleId排序
        String[] sortColummn = {"createDate", "articleId"};

        Specification<Article> specification = (root, query, cb) -> {
            Path<String> exp1 = root.get("tags");
            return cb.and(cb.like(exp1, "%" + tags + "%"));
        };

        Pageable pageable = PageRequest.of(page, PAGE_SIZE, new Sort(Sort.Direction.DESC, sortColummn));
        Page<Article> articlePage = articleDao.findAll(specification, pageable);
        return articlePage;
    }
}