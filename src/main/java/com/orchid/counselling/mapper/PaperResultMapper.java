package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.PaperResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PaperResultMapper {

    /**
     * 根据试题id查询试题结果
     * @param paperId 试题id
     * @return
     */
    public List<PaperResult> selectResultByPaperId(Integer paperId);

}
