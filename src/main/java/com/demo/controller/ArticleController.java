package com.demo.controller;


import com.demo.dao.ArticleDao;
import com.demo.domain.Article;
import com.demo.service.ArticleService;
import com.demo.utils.ConstantsUtils;
import com.demo.utils.MarkDownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;


/**
 * @author Mr.Deng
 * Created on 2018/3/27 15:29
 **/
@Controller
public class ArticleController {

    @Autowired
    ArticleDao articleDao;
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/article/{articleid}")
    public String viewArticle(@PathVariable int articleid, Model model) {
        //更新浏览数量
        articleService.updateArticleViewNum(articleid);

        //这一段放上面，如果model中article的数据和数据库不一样，update会更新article中的数据，
        //恰好findbyid方法将md格式转换成了html，导致update浏览数量时将数据库的md格式更新为HTML
        model.addAttribute("article", articleService.findById(articleid));
        return "article_viewPage";
    }

    /**
     * 文章编辑
     * Created on 2018/3/27 16:50
     **/
    @RequestMapping("/article/editor")
    public String articleEditor() {
        return "article_editor";
    }

    /**
     * 文章保存
     * Created on 2018/3/27 16:50
     **/
    @RequestMapping(value = "/article/save", method = {RequestMethod.POST})
    public String articleSave(Article article, Model model) {
        System.out.print(article.getContent());
        articleService.save(article);
        //获取首页文章列表
        Page<Article> articlePage = articleService.findArticlePage(0);
        model.addAttribute("articlePage", articlePage);
        return "index";
    }

    /**
     * 分页
     * page 页码 起始值1
     * tags 标签，可以为空
     * model 返回的数据集合
     * Created on 2018/3/28 14:05
     **/
    @RequestMapping("/article/page/{page}")
    public String getArticlePage(@PathVariable int page,
                                 @RequestParam(value = "queryTags", required = false) String tags,
                                 Model model) {
        //获取首页文章列表
        Page<Article> articlePage = articleService.getArticleByTags(tags, page - 1);
        //获取文章的预览
        Iterator<Article> it = articlePage.getContent().iterator();
        while (it.hasNext()) {
            Article article = it.next();
            article.setPreview(artcileSubStr(article.getContent(), ConstantsUtils.PREVIEW_SUBSTR_SIZE));
        }


        model.addAttribute("articlePage", articlePage);
        model.addAttribute("queryTags", tags);
        return "index";
    }

    /*  *//**
     * 分页
     * Created on 2018/3/28 14:05
     **//*
    @RequestMapping("/article/tags/{tags}")
    public String getArticleByTags(@PathVariable String tags,Model model) {
        //获取首页文章列表
        Page<Article> articlePage = articleService.getArticleByTags(tags,0);
        //获取文章的预览
        Iterator<Article> it = articlePage.getContent().iterator();
        while (it.hasNext()) {
            Article article = it.next();
            article.setPreview(artcileSubStr(article.getContent(), ConstantsUtils.PREVIEW_SUBSTR_SIZE));
        }
        model.addAttribute("articlePage", articlePage);
        return "index";
    }*/

    /**
     * 截取文章，显示预览
     *
     * @param content 文章内容
     * @param length  要截取文字的个数
     *                Created on 2018/3/28 10:32
     **/
    static String artcileSubStr(String content, int length) {
        String txt = MarkDownUtils.mdToText(content);
        if (txt.length() < length) length = txt.length();
        //转换成TXT
        return txt.substring(0, length);
    }
}
