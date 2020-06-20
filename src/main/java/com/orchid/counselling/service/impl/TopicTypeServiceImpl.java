package com.orchid.counselling.service.impl;

import com.orchid.counselling.mapper.TopicMapper;
import com.orchid.counselling.mapper.TopicTypeMapper;
import com.orchid.counselling.pojo.TopicType;
import com.orchid.counselling.service.TopicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicTypeServiceImpl implements TopicTypeService {

    @Autowired
    private TopicTypeMapper topicTypeMapper;

    @Override
    public List<TopicType> findAllType() {

        List<TopicType> topicTypes = topicTypeMapper.selectAllType();

        return topicTypes;
    }
}
