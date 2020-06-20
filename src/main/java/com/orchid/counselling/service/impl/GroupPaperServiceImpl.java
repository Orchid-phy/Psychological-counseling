package com.orchid.counselling.service.impl;

import com.orchid.counselling.mapper.GroupPaperMapper;
import com.orchid.counselling.pojo.GroupMy;
import com.orchid.counselling.pojo.GroupPaper;
import com.orchid.counselling.pojo.Paper;
import com.orchid.counselling.service.GroupPaperService;
import com.orchid.counselling.vo.ParaVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupPaperServiceImpl implements GroupPaperService {

    @Autowired
    private GroupPaperMapper groupPaperMapper;

    @Override
    public List<GroupPaper> getAllTaskByAdmin(Integer groupId, Integer paperId) {

        //获得用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();

        //构造查询参数
        GroupMy groupMy = new GroupMy(groupId);
        Paper paper = new Paper(paperId);
        GroupPaper groupPaper = new GroupPaper(groupMy, paper);
        ParaVo paraVo = new ParaVo();
        paraVo.setPara(username);
        paraVo.setGroupPaper(groupPaper);

        List<GroupPaper> groupPapers = groupPaperMapper.selectByAdminName(paraVo);

        return groupPapers;
    }
}
