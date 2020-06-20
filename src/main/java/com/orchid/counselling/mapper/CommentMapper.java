package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentMapper {

    /**
     * 添加评论信息
     * @param comment 评论信息实体
     * @return
     */
    public Integer inComments(Comment comment);

}
