package com.demo.service;

import com.demo.domain.Article;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author Mr.Deng
 * Created on 2018/3/27 13:46
 **/
public interface ArticleService {
    //分页
    Page<Article> findArticlePage();

    Article findById(int articleId);

    Article save(Article article);
}
