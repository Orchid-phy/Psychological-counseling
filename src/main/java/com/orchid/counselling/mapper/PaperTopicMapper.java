package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.PaperTopic;
import com.orchid.counselling.vo.ParaVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PaperTopicMapper {

    /**
     * 批量新增试题试卷关系
     * @param paraVo
     * @return
     */
    public Integer batchInTopic(ParaVo paraVo);

}
