package com.orchid.counselling.service.impl;

import com.orchid.counselling.mapper.PaperTopicMapper;
import com.orchid.counselling.mapper.TopicMapper;
import com.orchid.counselling.pojo.Paper;
import com.orchid.counselling.pojo.PaperTopic;
import com.orchid.counselling.pojo.Topic;
import com.orchid.counselling.pojo.TopicOptions;
import com.orchid.counselling.service.TopicService;
import com.orchid.counselling.util.ImportExcel;
import com.orchid.counselling.util.ObjectChange;
import com.orchid.counselling.vo.ParaVo;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private PaperTopicMapper paperTopicMapper;

    @Override
    public List<Topic> findAllTopicByPaperId(Integer id) {

        //查询参数
        PaperTopic paperTopic = new PaperTopic();
        Paper paper = new Paper();
        paper.setId(id);
        paperTopic.setPaper(paper);

        //查询数据
        List<Topic> topics = topicMapper.selAllTopicByPaperId(paperTopic);

        return topics;
    }

    @Override
    public List<Topic> findTopicByTopicId(Integer topicId) {
        //查询参数
        PaperTopic paperTopic = new PaperTopic();
        Topic topic = new Topic();
        topic.setId(topicId);
        paperTopic.setTopic(topic);

        //查询数据
        List<Topic> topics = topicMapper.selAllTopicByPaperId(paperTopic);

        return topics;
    }

    @Override
    public String batchTopic(MultipartFile file, Integer paperId) {

        //文件名
        String fileName = file.getOriginalFilename();

        //获取试题excel
        Map<String, Object> excel = ImportExcel.readTopicExcel(file, fileName);

        String errorInfo = excel.get("errorInfo").toString();

        //如果文件内容不对，返回错误信息
        if (!"".equals(errorInfo)){
            return errorInfo;
        }

        //获取试题信息
        List<Topic> topicList = ObjectChange.castList(excel.get("list"), Topic.class);
        //批量插入试题信息，并返回主键list
        topicMapper.batchTopic(topicList);

        //将结果构造为list
        List<Integer> batchTopic = new ArrayList<>();
        for (Topic topic: topicList) {
            batchTopic.add(topic.getId());
        }

        //构造添加参数
        ParaVo paraVo = new ParaVo();
        paraVo.setList(batchTopic);
        paraVo.setPara(paperId.toString());

        //添加数据
        Integer inTopic = paperTopicMapper.batchInTopic(paraVo);

        if (inTopic > 0 ){
            return "success";
        }

        return "error";
    }

}
