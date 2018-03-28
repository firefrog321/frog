package com.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文章实体类
 * Created by <a href='http://jcker.org'>Alan Turing</a>
 * on 2018-02-23 at 3:13 PM
 *
 * @version 1.0
 */
@Entity
@Table(name = "article")
public class Article implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "article_id", columnDefinition = "int(10) COMMENT'文章ID'") //表和实体类字段名不一样使用此注解
    private int articleId;

    @Column(columnDefinition = "TEXT COMMENT'内容'")
    private String content;

    @Column(columnDefinition = "varchar(255) COMMENT'标题'")
    private String title;

    @Column(name = "create_date", columnDefinition = "varchar(255) COMMENT'创建时间'")
    private String createDate;

    @Column(columnDefinition = "varchar(255) COMMENT'作者'")
    private String author;

    @Column(name = "view_num", columnDefinition = "int(10) COMMENT'浏览数量'")
    private int viewNum;

    @Column(columnDefinition = "varchar(255) COMMENT'标签'")
    private String tags;

    @Transient //不需要和数据库映射的属性
    private String preview;

    @Column(name = "is_page")
    private String isPage;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getIsPage() {
        return isPage;
    }

    public void setIsPage(String isPage) {
        this.isPage = isPage;
    }
}
