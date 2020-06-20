package com.orchid.counselling.service.impl;

import com.orchid.counselling.mapper.GroupMyMapper;
import com.orchid.counselling.mapper.UserMapper;
import com.orchid.counselling.pojo.GroupMy;
import com.orchid.counselling.pojo.User;
import com.orchid.counselling.service.GroupMyService;
import com.orchid.counselling.vo.ParaVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMyServiceImpl implements GroupMyService {

    @Autowired
    private GroupMyMapper groupMyMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<GroupMy> findAllGroupByCounselor(String id, String name) {

        //拼接查询参数
        GroupMy groupMy = new GroupMy();

        //id和团队名
        if (id != null && !"".equals(id)){
            groupMy.setGroupId(Integer.parseInt(id));
        }

        if (name != null && !"".equals(name)){
            name = "%" + name + "%";
        }
        groupMy.setGroupName(name);

        ParaVo paraVo = new ParaVo();
        paraVo.setGroupMy(groupMy);

        //获得登录用户的用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        //获取用户id
        Long id1 = userMapper.selUserIdByName(username);
        paraVo.setPara(id1.toString());

        //查询数据
        List<GroupMy> list = groupMyMapper.selAllGroupByCounselor(paraVo);

        return list;
    }

    @Override
    public List<GroupMy> findAllGroupByAdmin(String groupId, String groupName) {

        //获取登录用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = new User();
        user.setUsername(username);

        //构建查询参数
        GroupMy groupMy = new GroupMy();

        if (groupId != null){
            groupId = groupId.trim();
            groupMy.setGroupId(Integer.parseInt(groupId));
        }

        if (groupName != null){
            groupMy.setGroupName("%"+groupName+"%");
        }

        groupMy.setAdministrator(user);

        List<GroupMy> groupMyList = groupMyMapper.selAllGroupByAdmin(groupMy);

        return groupMyList;
    }

    @Override
    public String delById(Integer id) {

        Integer delById = groupMyMapper.delById(id);

        if (delById > 0){
            return "success";
        }

        return "error";
    }

    @Override
    public String addGroup(GroupMy groupMy) {

        String username = (String) SecurityUtils.getSubject().getPrincipal();

        User user = new User();
        user.setUsername(username);
        groupMy.setAdministrator(user);

        Integer inGroup = groupMyMapper.insertGroup(groupMy);

        if (inGroup > 0){
            return "success";
        }

        return "error";
    }

}
