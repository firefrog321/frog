package com.demo.controller;


import com.demo.dao.ArticleDao;
import com.demo.domain.Article;
import com.demo.service.ArticleService;
import com.demo.utils.ConstantsUtils;
import com.demo.utils.MarkDownUtils;
import com.github.pagehelper.PageInfo;
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
        articleService.save(article);
        //获取首页文章列表
        model.addAttribute("articlePage", articleService.findArticlePage("", 1));
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
        PageInfo articlePage = articleService.findArticlePage(tags, page);

        model.addAttribute("articlePage", articlePage);
        model.addAttribute("queryTags", tags);
        return "index";
    }


}
