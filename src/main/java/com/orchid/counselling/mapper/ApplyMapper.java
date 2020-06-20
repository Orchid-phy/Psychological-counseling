package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.Apply;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ApplyMapper {

    /**
     * 插入申请管理员记录
     * @param apply
     * @return
     */
    public Integer inAdminApply(Apply apply);

    /**
     * 插入心理咨询师申请请求
     * @param apply
     * @return
     */
    public Integer inCouncellingApply(Apply apply);

    /**
     * 查询管理员申请记录
     * @param applyUserId
     * @return
     */
    public List<Apply> selApply(Long applyUserId);

    /**
     * 查询咨询师申请记录
     * @param applyUserId
     * @return
     */
    public List<Apply> selCounselorApply(Long applyUserId);


}
