package com.demo.service.impl;

import com.demo.dao.ArticleDao;
import com.demo.domain.Article;
import com.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


/**
 * @author Mr.Deng
 * Created on 2018/3/27 13:52
 **/
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    private static final int PAGE_SIZE = 10; //每页获取的条数

    @Autowired
    ArticleDao articleDao;

    /**
     * 分页查询Article
     **/
    public Page<Article> findArticlePage() {
        //指定pageable对象 从0开始 根据create_date字段desc排序
        String[] sortColummn = {"createDate", "articleId"};
        Pageable pageable = PageRequest.of(0, PAGE_SIZE, new Sort(Sort.Direction.DESC, sortColummn));
        return articleDao.findAll(pageable);
    }

    @Override
    public Article findById(int articleId) {
        return articleDao.findById(articleId);
    }

    /**
     * 保存文章
     * Created on 2018/3/28 10:00
     **/
    @Override
    public Article save(Article article) {
        article.setAuthor("dengym");
        article.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        articleDao.save(article);
        return null;
    }


}