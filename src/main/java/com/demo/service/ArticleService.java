package com.demo.service;

import com.demo.domain.Article;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;

/**
 * @author Mr.Deng
 * Created on 2018/3/27 13:46
 **/
public interface ArticleService {

    /**
     *  获取文章，主要用于首页，还有标签查询
     * @param tags 标签，暂时只支持单个
     * @param page 页码，从1开始
     * @return article
     */
    PageInfo findArticlePage(String tags,int page);

    /**
     * 获取文章内容，MD文档没有转换
     * 不会使用redis
     * @param articleId id
     * @return article
     */
    Article findById(int articleId);

    /**
     * 获取展示的文章内容，MD文档会转换成HTML
     * Redis更新：先看缓存是否存在，不存在才去数据库获取，同时保存到缓存中
     * @param articleId id
     * @return article
     * @throws Exception e
     */
    Article getArticleToView(int articleId) throws Exception;

    /**
     * 保存文章
     * 同时刷新redis缓存
     * @param article article
     * @return null
     */
    Article save(Article article) ;


    /**
     * 更新浏览数量
     * @param articleId id
     **/
    void updateArticleViewNum(int articleId);

}
