package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.GroupMy;
import com.orchid.counselling.vo.ParaVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GroupMyMapper {

    /**
     * 查询该咨询师所在的团体
     * @param paraVo
     * @return
     */
    public List<GroupMy> selAllGroupByCounselor(ParaVo paraVo);

    /**
     * 查询管理员创建的团体
     * @param groupMy
     * @return
     */
    public List<GroupMy> selAllGroupByAdmin(GroupMy groupMy);

    /**
     * 根据id删除团体
     * @param id 团体id
     * @return
     */
    public Integer delById(Integer id);

    /**
     * 添加新的团体信息
     * @param groupMy
     * @return
     */
    public Integer insertGroup(GroupMy groupMy);

}
