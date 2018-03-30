package com.demo.controller;


import com.demo.dao.ArticleDao;
import com.demo.domain.Article;
import com.demo.service.ArticleService;
import com.demo.utils.MarkDownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        System.out.print(article.getContent());
        articleService.save(article);
        //获取首页文章列表
        Page<Article> articlePage = articleService.findArticlePage();
        model.addAttribute("articlePage", articlePage);
        return "index";
    }

    /**
     * 截取文章，显示预览
     *
     * @param content 文章内容
     * @param length  要截取文字的个数
     *                Created on 2018/3/28 10:32
     **/
    static String artcileSubStr(String content, int length) {
        if (content.length() < length) length = content.length();
        //转换成HTML
        return MarkDownUtils.mdToHtml(content.substring(0, length));
    }
}
