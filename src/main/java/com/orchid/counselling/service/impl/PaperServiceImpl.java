package com.orchid.counselling.service.impl;

import com.orchid.counselling.mapper.PaperMapper;
import com.orchid.counselling.pojo.Paper;
import com.orchid.counselling.pojo.TopicType;
import com.orchid.counselling.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;

    @Override
    public List<Paper> findAllPaperByIdOrType(String id, String typeName) {

        //构造查询条件
        Paper paper = new Paper();

        TopicType type = new TopicType();
        if (typeName != null && !"".equals(typeName)){
            typeName = "%" + typeName +"%";
        }
        type.setTypeName(typeName);

        if (id != null && !"".equals(id)){
            paper.setId(Integer.parseInt(id));
        }

        paper.setType(type);

        //查询数据
        List<Paper> papers = paperMapper.selAllPaperByIdOrType(paper);

        return papers;
    }

    @Override
    public List<Paper> findPaperTop() {

        List<Paper> papers = paperMapper.selPaperTop();

        return papers;
    }

    @Override
    public List<Paper> findPaperByType(String typeName) {

        List<Paper> papers = paperMapper.selPaperByType(typeName);

        return papers;
    }

    @Override
    public Paper findPaperById(Integer id) {

        Paper paper = paperMapper.selPaperById(id);

        return paper;
    }
}
