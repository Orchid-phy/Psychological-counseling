package com.orchid.counselling.service.impl;

import com.orchid.counselling.mapper.EvaluationRecordMapper;
import com.orchid.counselling.mapper.GroupPaperMapper;
import com.orchid.counselling.mapper.GroupUserMapper;
import com.orchid.counselling.mapper.UserMapper;
import com.orchid.counselling.pojo.*;
import com.orchid.counselling.service.EvaluationRecordService;
import com.orchid.counselling.vo.ParaVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测评结果记录
 */
@Service
public class EvaluationRecordServiceImpl implements EvaluationRecordService {

    @Autowired
    private EvaluationRecordMapper evaluationRecordMapper;

    @Autowired
    private GroupUserMapper groupUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GroupPaperMapper groupPaperMapper;

    @Override
    public Map<String, List<EvaluationRecord>> selTestReByName() {

        //获得登录用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();

        List<EvaluationRecord> evaluationRecords = evaluationRecordMapper.selTestReByName(username);

        Map<String, List<EvaluationRecord>> listMap=null;
        try {
             listMap = groupBillingDataByExcpBatchCode(evaluationRecords);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listMap;
    }

    @Override
    public List<EvaluationRecord> findAllTestByUsername(String date) {

        String username = (String) SecurityUtils.getSubject().getPrincipal();

        //构造查询参数
        ParaVo paraVo = new ParaVo();
        paraVo.setPara(username);
        if (date != null && !"".equals(date)){
            paraVo.setPara1(date);
        }

        List<EvaluationRecord> evaluationRecords = evaluationRecordMapper.selectAllTestByUsername(paraVo);

        return evaluationRecords;
    }

    @Override
    public List<EvaluationRecord> findEvaRecordByUsername(String username) {

        if (username != null && !"".equals(username)){
            username = "%" + username + "%";
        }

        List<EvaluationRecord> evaluationRecords = evaluationRecordMapper.selectEvaRecordByUsername(username);

        return evaluationRecords;
    }

    @Override
    public String postEvaluation(List<Integer> groups, List<Integer> papers, String stopDate) {

        //添加团队试题信息
        ParaVo paraVo1 = new ParaVo();
        paraVo1.setList(groups);
        paraVo1.setList2(papers);
        paraVo1.setPara(stopDate);
        Integer inGroupPaper = groupPaperMapper.inTask(paraVo1);

        //获取登录用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();

        //获取用户id
        Long id = userMapper.selUserIdByName(username);

        User user = new User();
        user.setUserId(id);

        EvaluationRecord evaluationRecord = new EvaluationRecord();
        evaluationRecord.setReleaseUser(user);
        evaluationRecord.setStopDate(stopDate);


        //构造参数
        ParaVo paraVo = new ParaVo();
        paraVo.setList2(papers);
        paraVo.setEvaluationRecord(evaluationRecord);

        Integer isAdd = 0;

        //遍历团队数组
        for (int i = 0; i < groups.size(); i++) {

            isAdd = 0;

            //查询该团队有哪些成员
            List groupUserId = groupUserMapper.selUserByGroupId(groups.get(i));

            //将团队中的成员id放入参数类
            paraVo.setList(groupUserId);

            //给团体中的每个成员添加测试任务
            if(groupUserId.size() != 0){
                isAdd = evaluationRecordMapper.insertEvaluation(paraVo);

            }
        }

        if (isAdd > 0 && inGroupPaper > 0){
            return "success";
        }

        return "error";
    }

    @Override
    public Boolean upScore(EvaluationRecord record) {

        Integer upScore = evaluationRecordMapper.upScore(record);

        if (upScore > 0){
            return true;
        }

        return false;
    }

    /**
     * 按照截止日期进行分组
     * @param billingList
     * @return
     * @throws Exception
     */
    private Map<String, List<EvaluationRecord>> groupBillingDataByExcpBatchCode(List<EvaluationRecord> billingList) throws Exception{

        Map<String, List<EvaluationRecord>> resultMap = new HashMap<String, List<EvaluationRecord>>();

        try{

            for(EvaluationRecord tmExcpNew : billingList){

                if(resultMap.containsKey(tmExcpNew.getStopDate().substring(0,10))){

//                    List<EvaluationRecord> recordList = new ArrayList<>();
//                    recordList.add(tmExcpNew);

                    resultMap.get(tmExcpNew.getStopDate().substring(0,10)).add(tmExcpNew);

                }else{//map中不存在，新建key，用来存放数据

                    List<EvaluationRecord> tmpList = new ArrayList<EvaluationRecord>();

                    tmpList.add(tmExcpNew);

                    resultMap.put(tmExcpNew.getStopDate().substring(0,10), tmpList);
                }
            }
        }catch(Exception e){
            throw new Exception("出现异常", e);
        }

        return resultMap;
    }

}

