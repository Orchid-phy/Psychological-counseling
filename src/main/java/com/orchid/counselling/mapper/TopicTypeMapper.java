package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.TopicType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TopicTypeMapper {

    /**
     * 查找所有类型
     * @return
     */
    public List<TopicType> selectAllType();

}
