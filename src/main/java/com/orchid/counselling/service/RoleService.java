package com.orchid.counselling.service;


import java.util.Set;

/**
 * 角色
 */
public interface RoleService {

    /**
     * 根据角色id查询角色名称
     * @return
     */
    public Set<String> findRoleNameById(Integer roleId);

}
