package com.orchid.counselling.service;

import com.orchid.counselling.pojo.ConsultingRecords;
import com.orchid.counselling.pojo.EvaluationRecord;
import com.orchid.counselling.vo.ParaVo;

import java.util.List;
import java.util.Map;

/**
 * 测评结果记录
 */
public interface EvaluationRecordService {

    /**
     * 根据登录用户查询用户未来时间的测试
     * @return
     */
    public Map<String, List<EvaluationRecord>> selTestReByName();

    /**
     * 查询当前用户所有的测试记录
     * @param date 发布日期
     * @return
     */
    public List<EvaluationRecord> findAllTestByUsername(String date);

    /**
     * 根据用户名查询咨询记录
     * @param username
     * @return
     */
    public List<EvaluationRecord> findEvaRecordByUsername(String username);

    /**
     * 管理员发布测试任务
     * @param groups 团体信息
     * @param papers 试题信息
     * @param stopDate 截止日期
     * @return
     */
    public String postEvaluation(List<Integer> groups, List<Integer> papers, String stopDate);

    /**
     * 记录测试结果
     * @param record
     * @return
     */
    public Boolean upScore(EvaluationRecord record);

}

