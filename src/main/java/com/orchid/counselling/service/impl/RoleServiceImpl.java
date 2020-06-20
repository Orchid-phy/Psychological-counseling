package com.orchid.counselling.service.impl;


import com.orchid.counselling.mapper.RoleMapper;
import com.orchid.counselling.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Set<String> findRoleNameById(Integer roleId) {

        Set<String> roles = new HashSet<>();
        roles = roleMapper.selectRoleNameById(roleId);

        return roles;
    }
}
