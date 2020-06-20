package com.orchid.counselling.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orchid.counselling.pojo.Article;
import com.orchid.counselling.service.ArticleService;
import com.orchid.counselling.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 查询文章信息，可以根据文章的发表用户名查询
     * @param pageNum
     * @param authorName
     * @return
     */
    @GetMapping("/getArticleByAuthorName")
    @ResponseBody
    public JsonResult getArticleByAuthorName(@RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,String authorName){

        PageHelper.startPage(pageNum, 8);
        List<Article> articles = articleService.findArticleByUsername(authorName);

        PageInfo<Article> articlePageInfo = new PageInfo<>(articles);

        return new JsonResult(200, "success", articlePageInfo);
    }

    /**
     * 用户发表文章
     * @param article
     * @return
     */
    @PostMapping("/addArticle")
    @ResponseBody
    public JsonResult addArticle(Article article){

        //添加新的文章信息
        Boolean addArticle = articleService.addArticle(article);

        if (addArticle){
            return new JsonResult(200, "success");
        }

        return new JsonResult(4001, "error");
    }

}
