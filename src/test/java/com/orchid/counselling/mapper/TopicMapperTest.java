package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.Topic;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TopicMapperTest {

    @Autowired
    TopicMapper topicMapper;

    @Test
    void batchTopic() {

        Topic topic = new Topic();
        topic.setTestQuestion("test");
        topic.setDescription("des");

        Topic topic2 = new Topic();
        topic2.setTestQuestion("test");
        topic2.setDescription("des");

        List<Topic> topicList = new ArrayList<>();
        topicList.add(topic);
        topicList.add(topic2);

        topicMapper.batchTopic(topicList);

        for (Topic t : topicList) {

            System.out.println(t.getId());

        }


    }
}