package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.EvaluationRecord;
import com.orchid.counselling.vo.ParaVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 测评结果记录
 */
@Component
public interface EvaluationRecordMapper {

    /**
     * 查询登录用户的测试
     * @param username
     * @return
     */
    public List<EvaluationRecord> selTestReByName(String username);

    /**
     * 查询当前用户所有的测试记录
     * @param paraVo
     * @return
     */
    public List<EvaluationRecord> selectAllTestByUsername(ParaVo paraVo);

    /**
     * 根据用户名查询咨询记录
     * @param username
     * @return
     */
    public List<EvaluationRecord> selectEvaRecordByUsername(String username);

    /**
     * 插入测试任务记录
     * @param paraVo
     * @return
     */
    public Integer insertEvaluation(ParaVo paraVo);

    /**
     * 记录测试结果
     * @param record
     * @return
     */
    public Integer upScore(EvaluationRecord record);

}

