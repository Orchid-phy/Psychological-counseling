package com.orchid.counselling.service.impl;

import com.orchid.counselling.mapper.CommentMapper;
import com.orchid.counselling.pojo.Comment;
import com.orchid.counselling.pojo.Confide;
import com.orchid.counselling.pojo.User;
import com.orchid.counselling.service.CommentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.security.Security;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Boolean addComments(Integer userId, Integer confideId, String comments) {

        //构建评论用户的信息
        User user = new User();
        user.setUserId(userId);

        //动态信息
        Confide confide = new Confide();
        confide.setId(confideId);

        //添加参数
        Comment comment = new Comment(confide, user, comments);

        //执行插入评论信息操作
        Integer inComments = commentMapper.inComments(comment);

        if (inComments > 0){
            return true;
        }

        return false;
    }
}
