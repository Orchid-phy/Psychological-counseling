package com.orchid.counselling.service;

import com.orchid.counselling.pojo.TopicType;

import java.util.List;

public interface TopicTypeService {

    /**
     * 查找所有类型
     * @return
     */
    public List<TopicType> findAllType();

}
