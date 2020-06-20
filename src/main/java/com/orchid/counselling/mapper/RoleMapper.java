package com.orchid.counselling.mapper;


import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 角色
 */
@Component
public interface RoleMapper {

    /**
     * 根据角色id查询角色名称
     * @return
     */
    public Set<String> selectRoleNameById(Integer roleId);

}
