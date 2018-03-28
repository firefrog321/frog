package com.demo.dao;

import com.demo.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Mr.Deng
 * Created on 2018/3/27 13:52
 **/
public interface ArticleDao extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {

    Page<Article> findAll(Pageable pageable);

    Article findById(int articleId);
}
