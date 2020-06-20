package com.orchid.counselling.service.impl;

import com.orchid.counselling.mapper.ArticleMapper;
import com.orchid.counselling.pojo.Article;
import com.orchid.counselling.pojo.User;
import com.orchid.counselling.service.ArticleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> findArticleByUsername(String authorName) {

        if (authorName != null){
            authorName = "%" + authorName + "%";
        }

        List<Article> articles = articleMapper.selArticleByUsername(authorName);

        return articles;
    }

    @Override
    public Boolean addArticle(Article article) {

        //获取登录用户的用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        //构造参数
        User user = new User(username);
        article.setAuthor(user);
        //执行插入操作
        Integer integer = articleMapper.insertArticle(article);

        if (integer > 0){
            return true;
        }

        return false;
    }
}
