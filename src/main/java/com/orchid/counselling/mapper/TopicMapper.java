package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.PaperTopic;
import com.orchid.counselling.pojo.Topic;
import com.orchid.counselling.pojo.TopicOptions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TopicMapper {

    /**
     * 根据试题id查询所有题目
     * @param paperTopic
     * @return
     */
    public List<Topic> selAllTopicByPaperId(PaperTopic paperTopic);

    /**
     * 批量新增试题信息
     * @param topics
     * @return
     */
    public Integer batchTopic(List<Topic> topics);

}
