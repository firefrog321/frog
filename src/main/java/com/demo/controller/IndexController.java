package com.demo.controller;

import com.demo.dao.UserDao;
import com.demo.domain.Article;
import com.demo.domain.User;
import com.demo.service.ArticleService;
import com.demo.utils.MarkDownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;

import static com.demo.controller.ArticleController.artcileSubStr;

/**
 * @author Mr.Deng
 * Created on 2018/3/26 15:26
 **/
@Controller
public class IndexController {
    private static final int SUBSTR_SIZE = 100; //截取字符串长度

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleService articleService;

    /**
     * 主页
     * Created on 2018/3/28 14:05
     **/
    @RequestMapping("/")
    public String index(Model model) {
        //获取首页文章列表
        Page<Article> articlePage = articleService.findArticlePage();
        //获取文章的预览
        Iterator<Article> it = articlePage.getContent().iterator();
        while (it.hasNext()) {
            Article article = it.next();
            article.setPreview(artcileSubStr(article.getContent(), SUBSTR_SIZE));
        }
        model.addAttribute("articlePage", articlePage);
        return "index";
    }

    @RequestMapping("/user")
    @ResponseBody
    public List<User> user() {
        return userDao.findAll();
    }
}
