package com.demo.controller;

import com.demo.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mr.Deng
 * Created on 2018/3/26 15:26
 **/
@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    /**
     * 主页
     * Created on 2018/3/28 14:05
     **/
    @RequestMapping("/")
    public String index(Model model) throws Exception {
        PageInfo articlePage = articleService.findArticlePage("", 1);
        //获取首页文章列表
        model.addAttribute("articlePage", articlePage);
        return "index";
    }
}
