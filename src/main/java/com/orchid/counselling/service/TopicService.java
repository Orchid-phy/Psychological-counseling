package com.orchid.counselling.service;


import com.orchid.counselling.pojo.Topic;
import com.orchid.counselling.pojo.TopicOptions;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TopicService {

    /**
     * 根据试题id查询所有题目
     * @param id 试题id
     * @return
     */
    public List<Topic> findAllTopicByPaperId(Integer id);

    /**
     * 根据试题id查询试题信息
     * @return
     */
    public List<Topic> findTopicByTopicId(Integer topicId);

    /**
     * 批量新增试题信息
     * @param file
     * @return
     */
    public String batchTopic(MultipartFile file, Integer paperId);

}
