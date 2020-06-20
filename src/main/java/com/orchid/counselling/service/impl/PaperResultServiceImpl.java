package com.orchid.counselling.service.impl;

import com.orchid.counselling.mapper.PaperResultMapper;
import com.orchid.counselling.pojo.PaperResult;
import com.orchid.counselling.service.PaperResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaperResultServiceImpl implements PaperResultService {

    @Autowired
    private PaperResultMapper paperResultMapper;

    public List<PaperResult> findResultByPaperId(Integer paperId){

        List<PaperResult> results = paperResultMapper.selectResultByPaperId(paperId);

        return results;
    }

}
