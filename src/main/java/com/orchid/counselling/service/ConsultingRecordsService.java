package com.orchid.counselling.service;


import com.orchid.counselling.pojo.ConsultingRecords;
import com.orchid.counselling.pojo.EvaluationRecord;

import java.util.List;
import java.util.Map;

/**
 * 用户咨询记录
 */
public interface ConsultingRecordsService {

    /**
     * 根据当前用户查询用户咨询记录
     * @return
     */
    public List<ConsultingRecords> findAllInfoByUsername(String counselors);

    /**
     * 根据登录用户查询用户未来时间的预约
     * @return
     */
    public Map<String, List<ConsultingRecords>> selConsultingReByName();

    /**
     * 咨询师未来时间的预约
     * @return
     */
    public Map<String, List<ConsultingRecords>> selCounselorConsultingReByName();

    /**
     * 根据用户id查询用户的详细信息
     * @param username
     * @return
     */
    public List<ConsultingRecords> findConsultingRecordByUserId(String username);

    /**
     * 取消预约
     * @param id
     * @return
     */
    public Boolean delRecord(Integer id);

    /**
     * 添加咨询预约记录，用户点击预约
     * @return
     */
    public Boolean addEvaluationRecord(ConsultingRecords consultingRecords);

}
