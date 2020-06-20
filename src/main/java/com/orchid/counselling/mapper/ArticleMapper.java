package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.Article;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ArticleMapper {

    /**
     * 查询文章信息，可以根据作者的用户名查询
     * @param authorName
     * @return
     */
    public List<Article> selArticleByUsername(String authorName);

    /**
     * 添加文章信息
     * @param article
     * @return
     */
    public Integer insertArticle(Article article);

}
