package com.orchid.counselling.service.impl;

import com.orchid.counselling.mapper.ConfideMapper;
import com.orchid.counselling.mapper.UserMapper;
import com.orchid.counselling.pojo.Confide;
import com.orchid.counselling.service.ConfideService;
import com.orchid.counselling.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConfideServiceImpl implements ConfideService {

    @Autowired
    public ConfideMapper confideMapper;

    @Autowired
    public UserMapper userMapper;

    @Override
    public List<Confide> findCommentByConId() {

        List<Confide> confides = confideMapper.selMessage(null);

        return confides;
    }

    @Override
    public List<Confide> findCommentByConIdByUserId() {

        //获取登录用户的用户信息
        String username = (String) SecurityUtils.getSubject().getPrincipal();

        //获取用户id
        Long id = userMapper.selUserIdByName(username);

        List<Confide> confides = confideMapper.selMessage(id);

        return confides;
    }
}
