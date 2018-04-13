package com.demo.dao;

import com.demo.domain.Article;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleDao {
    /**
     * @param article article
     * @return int
     **/
    void insert(Article article);

    /**
     * @param
     * @return article
     **/
    Article selectByPrimaryKey(Integer articleId);

    /**
     * @param map map
     * @return list
     **/
    List<Article> findArticle(Map map);


    /**
     * 更新浏览数量
     *
     * @param articleId 主键
     **/
    @Update("update article set view_num= view_num + 1 where article_id =#{articleId}")
    void updateViewNum(int articleId);

    /**
     * 更新编辑的内容,主要有标题，标签，内容
     * @param article article
     */
    void updateArticleEditor(Article article);
}