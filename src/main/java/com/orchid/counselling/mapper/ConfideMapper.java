package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.Comment;
import com.orchid.counselling.pojo.Confide;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ConfideMapper {

    /**
     * 查询用户发布的动态消息
     * @param userId 可选参数
     * @return
     */
    public List<Confide> selMessage(Long userId);

}
