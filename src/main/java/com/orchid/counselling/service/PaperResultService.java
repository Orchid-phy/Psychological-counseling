package com.orchid.counselling.service;

import com.orchid.counselling.pojo.PaperResult;

import java.util.List;

public interface PaperResultService {

    /**
     * 根据试题id查询试题测试结果
     * @param paperId
     * @return
     */
    public List<PaperResult> findResultByPaperId(Integer paperId);

}
