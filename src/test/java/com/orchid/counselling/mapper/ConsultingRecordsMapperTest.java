package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.ConsultingRecords;
import com.orchid.counselling.vo.ParaVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ConsultingRecordsMapperTest {

    @Autowired
    ConsultingRecordsMapper consultingRecordsMapper;

    @Test
    void selectAllInfoByUsername() {

        ParaVo paraVo = new ParaVo();
        paraVo.setPara("orchid");
        paraVo.setPara1("%root%");
//        paraVo.setPara2("2020-2-23");

        List<ConsultingRecords> list = consultingRecordsMapper.selectAllInfoByUsername(paraVo);

        for (ConsultingRecords c:list
             ) {
            System.out.println(c.getTime());
        }

//        List<ConsultingRecords> list = consultingRecordsMapper.selConsultingReByName("orchid");

//        //根据预约时间分类
//        try {
//            Map<String, List<ConsultingRecords>> map = groupBillingDataByExcpBatchCode(list);
//
//            for (String key: map.keySet()) {
//                System.out.println(key+ " " +map.get(key));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    /**
     * 按照异常批次号对已开单数据进行分组
     * @param billingList
     * @return
     * @throws Exception
     */
    private Map<String, List<ConsultingRecords>> groupBillingDataByExcpBatchCode(List<ConsultingRecords> billingList) throws Exception{

        Map<String, List<ConsultingRecords>> resultMap = new HashMap<String, List<ConsultingRecords>>();

        try{

            for(ConsultingRecords tmExcpNew : billingList){

                if(resultMap.containsKey(tmExcpNew.getTime().substring(0,10))){//map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中

                    resultMap.get(tmExcpNew.getTime().substring(0,10)).add(tmExcpNew);

                }else{//map中不存在，新建key，用来存放数据

                    List<ConsultingRecords> tmpList = new ArrayList<ConsultingRecords>();

                    tmpList.add(tmExcpNew);

                    resultMap.put(tmExcpNew.getTime().substring(0,10), tmpList);
                }
            }
        }catch(Exception e){
            throw new Exception("按照异常批次号对已开单数据进行分组时出现异常", e);
        }

        return resultMap;
    }
}