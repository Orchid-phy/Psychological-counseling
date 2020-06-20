package com.orchid.counselling.service;

import com.orchid.counselling.pojo.Comment;

public interface CommentService {

    /**
     * 添加评论信息
     * @param confideId 动态ID
     * @param comments 评论内容
     * @return
     */
    public Boolean addComments(Integer userId, Integer confideId, String comments);

}
