package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.TopicType;
import com.orchid.counselling.service.TopicTypeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TopicTypeMapperTest {

    @Autowired
    TopicTypeMapper topicTypeMapper;

    @Autowired
    TopicTypeService topicTypeService;

    @Test
    void selectAllType() {

//        List<TopicType> topicTypes = topicTypeMapper.selectAllType();

        List<TopicType> topicTypes = topicTypeService.findAllType();

        for (TopicType t:topicTypes
             ) {

            System.out.println(topicTypes);

        }

    }
}