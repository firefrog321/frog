package com.demo.service;

import com.demo.domain.Article;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;

/**
 * @author Mr.Deng
 * Created on 2018/3/27 13:46
 **/
public interface ArticleService {
    //分页
    PageInfo findArticlePage(String tags,int page);

    Article findById(int articleId);

    Article save(Article article);

    //更新浏览数量
    void updateArticleViewNum(int articleId);

}
