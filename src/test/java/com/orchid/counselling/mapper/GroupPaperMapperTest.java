package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.GroupMy;
import com.orchid.counselling.pojo.GroupPaper;
import com.orchid.counselling.pojo.Paper;
import com.orchid.counselling.vo.ParaVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class GroupPaperMapperTest {

    @Autowired
    GroupPaperMapper groupPaperMapper;

    @Test
    void selectByAdminName() {

        GroupMy groupMy = new GroupMy();
        Paper paper = new Paper();

        GroupPaper groupPaper = new GroupPaper(groupMy, paper);

        ParaVo paraVo = new ParaVo();
        paraVo.setGroupPaper(groupPaper);
        paraVo.setPara("root");

        List<GroupPaper> groupPapers = groupPaperMapper.selectByAdminName(paraVo);

        for (GroupPaper group : groupPapers) {
            System.out.println(group.toString());
        }

    }
}