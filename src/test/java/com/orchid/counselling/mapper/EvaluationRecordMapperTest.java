package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.EvaluationRecord;
import com.orchid.counselling.pojo.User;
import com.orchid.counselling.vo.ParaVo;
import org.apache.shiro.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class EvaluationRecordMapperTest {

    @Autowired
    EvaluationRecordMapper evaluationRecordMapper;

    @Autowired
    GroupUserMapper groupUserMapper;

    @Autowired
    UserMapper userMapper;

    @Test
    void selTestReByName() {

        List<EvaluationRecord> records = evaluationRecordMapper.selTestReByName("orchid");

        for (EvaluationRecord re:records) {
            System.out.println(re);
        }

    }

    @Test
    void selectEvaRecordByUsername() {

//        ParaVo paraVo = new ParaVo();
//        paraVo.setPara("orchid");
//
//        List<EvaluationRecord> records = evaluationRecordMapper.selectAllTestByUsername(paraVo);
//
//        for (EvaluationRecord re:records) {
//            System.out.println(re);
//        }

        List<EvaluationRecord> records = evaluationRecordMapper.selectEvaRecordByUsername("%orchid%");

        for (EvaluationRecord re:records) {
            System.out.println(re);
        }


    }

    @Test
    void insert(){

        //获取用户id
        Long id = userMapper.selUserIdByName("root");

        EvaluationRecord evaluationRecord = new EvaluationRecord();
        evaluationRecord.setStopDate("2020-2-26");

        User user = new User();
        user.setUserId(id);
        evaluationRecord.setReleaseUser(user);

        //构造参数
        ParaVo paraVo = new ParaVo();
        paraVo.setEvaluationRecord(evaluationRecord);

        List<Integer> groups = new ArrayList<>();
        groups.add(1);
        groups.add(3);

        List<Integer> papers = new ArrayList<>();
        papers.add(1);
        papers.add(2);

        paraVo.setList2(papers);

        Integer isAdd = 0;

        //遍历团队数组
        for (int i = 0; i < groups.size(); i++) {

            isAdd = 0;

            //查询该团队有哪些成员
            List groupUserId = groupUserMapper.selUserByGroupId(groups.get(i));

            //构造查询参数
            paraVo.setList(groupUserId);

            isAdd = evaluationRecordMapper.insertEvaluation(paraVo);

        }

        System.out.println(isAdd);

    }
}