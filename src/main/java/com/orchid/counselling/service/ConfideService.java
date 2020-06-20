package com.orchid.counselling.service;

import com.orchid.counselling.pojo.Confide;

import java.util.List;

public interface ConfideService {

    /**
     * 查询所有的动态消息
     * @return
     */
    public List<Confide> findCommentByConId();

    /**
     * 查询所有的动态消息
     * @return
     */
    public List<Confide> findCommentByConIdByUserId();

}
