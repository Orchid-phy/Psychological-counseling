package com.orchid.counselling.mapper;


import com.orchid.counselling.pojo.ConsultingRecords;
import com.orchid.counselling.pojo.EvaluationRecord;
import com.orchid.counselling.vo.ParaVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户咨询记录
 */
@Component
public interface ConsultingRecordsMapper {

    /**
     * 根据当前用户查询用户咨询记录
     * @param paraVo
     * @return
     */
    public List<ConsultingRecords> selectAllInfoByUsername(ParaVo paraVo);

    /**
     * 根据登录用户查询用户的预约
     * @param username
     * @return
     */
    public List<ConsultingRecords> selConsultingReByName(String username);


    /**
     * 咨询师的预约
     * @param username
     * @return
     */
    public List<ConsultingRecords> selCounselorConsultingReByName(String username);

    /**
     * 根据用户名查询用户的详细信息
     * @param username
     * @return
     */
    public List<ConsultingRecords> selConsultingRecordByUserId(String username);

    /**
     * 取消预约
     * @param id
     * @return
     */
    public Integer delRecord(Integer id);

    /**
     * 添加咨询预约记录，用户点击预约
     * @return
     */
    public Integer insertRecord(ConsultingRecords consultingRecords);

}
