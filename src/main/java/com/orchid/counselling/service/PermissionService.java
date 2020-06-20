package com.orchid.counselling.service;

import java.util.Set;

/**
 * 权限
 */
public interface PermissionService {

    /**
     * 根据角色id查询角色权限
     * @param roleId
     * @return
             */
    public Set<String> findPermsById(int roleId);

}
