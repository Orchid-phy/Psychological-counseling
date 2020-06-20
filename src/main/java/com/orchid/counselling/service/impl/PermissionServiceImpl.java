package com.orchid.counselling.service.impl;

import com.orchid.counselling.mapper.PermissionMapper;
import com.orchid.counselling.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 权限
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    public PermissionMapper permissionMapper;

    @Override
    public Set<String> findPermsById(int roleId) {


        Set<String> perms = permissionMapper.selectPermsById(roleId);

        return perms;
    }
}
