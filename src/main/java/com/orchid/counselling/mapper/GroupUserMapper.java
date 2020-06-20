package com.orchid.counselling.mapper;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GroupUserMapper {

    /**
     * 格局团队id查询所有团队成员
     * @param id
     * @return
     */
    public List selUserByGroupId(Integer id);

}
