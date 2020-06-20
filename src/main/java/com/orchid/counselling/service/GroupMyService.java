package com.orchid.counselling.service;

import com.orchid.counselling.pojo.GroupMy;

import java.util.List;

public interface GroupMyService {

    /**
     * 查询咨询师同一个团队的团体信息
     * @param id  团队id
     * @param name 团队名字
     * @return
     */
    public List<GroupMy> findAllGroupByCounselor(String id, String name);

    /**
     * 查询管理员创建的团体
     * @param
     * @return
     */
    public List<GroupMy> findAllGroupByAdmin(String groupId, String groupName);

    /**
     * 根据id删除团体
     * @param id 团体id
     * @return
     */
    public String delById(Integer id);

    /**
     * 添加新的团体信息
     * @param groupMy
     * @return
     */
    public String addGroup(GroupMy groupMy);

}
