package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.GroupPaper;
import com.orchid.counselling.vo.ParaVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GroupPaperMapper {


    /**
     * 添加团体任务信息
     * @param paraVo
     * @return
     */
    public Integer inTask(ParaVo paraVo);

    /**
     * 查询管理员发布的任务
     * @param paraVo
     * @return
     */
    public List<GroupPaper> selectByAdminName(ParaVo paraVo);



}
