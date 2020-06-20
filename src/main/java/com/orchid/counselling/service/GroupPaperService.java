package com.orchid.counselling.service;

import com.orchid.counselling.pojo.GroupPaper;

import java.util.List;

public interface GroupPaperService {


    /**
     * 根据管理员查询发布的任务信息
     * @param groupId
     * @param paperId
     * @return
     */
    public List<GroupPaper> getAllTaskByAdmin(Integer groupId, Integer paperId);

}
