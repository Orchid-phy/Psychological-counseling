package com.orchid.counselling.service.impl;


import com.orchid.counselling.mapper.ConsultingRecordsMapper;
import com.orchid.counselling.mapper.EvaluationRecordMapper;
import com.orchid.counselling.pojo.ConsultingRecords;
import com.orchid.counselling.pojo.EvaluationRecord;
import com.orchid.counselling.pojo.User;
import com.orchid.counselling.service.ConsultingRecordsService;
import com.orchid.counselling.service.EvaluationRecordService;
import com.orchid.counselling.vo.ParaVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 用户咨询记录
 */
@Service
public class ConsultingRecordsServiceImpl implements ConsultingRecordsService {

    @Autowired
    private ConsultingRecordsMapper consultingRecordsMapper;

    @Override
    public List<ConsultingRecords> findAllInfoByUsername(String counselors) {

        //获取登录用户的用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();

        //定义查询参数
        ParaVo paraVo = new ParaVo();
        paraVo.setPara(username);
        if (counselors != null && !"".equals(counselors)){
            paraVo.setPara1( "%" + counselors + "%");
        }

        List<ConsultingRecords> records = consultingRecordsMapper.selectAllInfoByUsername(paraVo);

        return records;
    }

    @Override
    public Map<String, List<ConsultingRecords>> selConsultingReByName() {

        //获取登录用户的用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();

        //查询登录用户的预约
        List<ConsultingRecords> records = consultingRecordsMapper.selConsultingReByName(username);

        Map<String, List<ConsultingRecords>> map = null;
        //根据预约时间分类
        try {
            map = groupBillingDataByExcpBatchCode(records);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }


    @Override
    public Map<String, List<ConsultingRecords>> selCounselorConsultingReByName() {

        //获取登录用户的用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();

        //查询登录用户的预约
        List<ConsultingRecords> records = consultingRecordsMapper.selCounselorConsultingReByName(username);

        Map<String, List<ConsultingRecords>> map = null;
        //根据预约时间分类
        try {
            map = groupBillingDataByExcpBatchCode(records);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public List<ConsultingRecords> findConsultingRecordByUserId(String username) {

        if (username != null && !"".equals(username) ){
            username = "%" + username + "%";
        }

        List<ConsultingRecords> consultingRecords = consultingRecordsMapper.selConsultingRecordByUserId(username);

        return consultingRecords;
    }

    @Override
    public Boolean delRecord(Integer id) {

        Integer idDel = consultingRecordsMapper.delRecord(id);

        if (idDel > 0){
            return true;
        }

        return false;
    }

    @Override
    public Boolean addEvaluationRecord(ConsultingRecords consultingRecords) {

        //获取登录用户的信息
        String username = (String) SecurityUtils.getSubject().getPrincipal();

        //构造参数
        User user = new User();
        user.setUsername(username);
        consultingRecords.setUser(user);

        //插入数据
        Integer record = consultingRecordsMapper.insertRecord(consultingRecords);

        if (record > 0){
            return true;
        }

        return false;
    }

    /**
     * 按照预约时间数据进行分组
     * @param billingList
     * @return
     * @throws Exception
     */
    private Map<String, List<ConsultingRecords>> groupBillingDataByExcpBatchCode(List<ConsultingRecords> billingList) throws Exception{

        Map<String, List<ConsultingRecords>> resultMap = new HashMap<String, List<ConsultingRecords>>();

        try{

            for(ConsultingRecords tmExcpNew : billingList){

                if(resultMap.containsKey(tmExcpNew.getTime().substring(0,10))){

                    resultMap.get(tmExcpNew.getTime().substring(0,10)).add(tmExcpNew);

                }else{//map中不存在，新建key，用来存放数据

                    List<ConsultingRecords> tmpList = new ArrayList<ConsultingRecords>();

                    tmpList.add(tmExcpNew);

                    resultMap.put(tmExcpNew.getTime().substring(0,10), tmpList);
                }
            }
        }catch(Exception e){
            throw new Exception("出现异常", e);
        }

        return resultMap;
    }


}
