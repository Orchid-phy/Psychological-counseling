package com.orchid.counselling.mapper;

import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 权限
 */
@Component
public interface PermissionMapper {

    /**
     * 根据角色id查询角色权限
      * @param roleId
     * @return
     */
    public Set<String> selectPermsById(int roleId);

}
