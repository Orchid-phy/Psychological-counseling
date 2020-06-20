package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.GroupMy;
import com.orchid.counselling.pojo.Paper;
import com.orchid.counselling.pojo.TopicType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PaperMapperTest {

    @Autowired
    PaperMapper paperMapper;

    @Test
    void selAllPaperByIdOrType() {

        Paper paper = new Paper();

        String typeName = null;
        String id = null;

        TopicType type = new TopicType();
        if (typeName != null && !"".equals(typeName)){
            typeName = "%" + typeName +"%";
        }
        type.setTypeName(typeName);

        if (id != null && !"".equals(id)){
            paper.setId(Integer.parseInt(id));
        }

        paper.setType(type);

        List<Paper> papers = paperMapper.selAllPaperByIdOrType(paper);

        for (Paper p:papers
             ) {
            System.out.println(p);
        }

    }
}