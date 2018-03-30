package com.demo.dao;

import com.demo.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Mr.Deng
 * Created on 2018/3/27 13:52
 **/
public interface ArticleDao extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {

    Page<Article> findAll(Pageable pageable);

    Article findById(int articleId);

    //更新浏览数量
    @Modifying
    @Query("update Article set view_num= view_num + 1 where articleId =?1")
    void updateArticleViewNum(int articleId);
}
