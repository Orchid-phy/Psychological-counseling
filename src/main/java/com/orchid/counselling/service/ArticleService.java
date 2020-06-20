package com.orchid.counselling.service;

import com.orchid.counselling.pojo.Article;

import java.util.List;

public interface ArticleService {

    /**
     * 查询文章信息，可以根据作者的用户名查询
     * @param authorName
     * @return
     */
    public List<Article> findArticleByUsername(String authorName);

    /**
     * 添加文章信息
     * @param article
     * @return
     */
    public Boolean addArticle(Article article);

}
