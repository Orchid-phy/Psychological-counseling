package com.orchid.counselling.controller;

import com.orchid.counselling.pojo.GroupMy;
import com.orchid.counselling.service.GroupMyService;
import com.orchid.counselling.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GroupMyContoller {

    @Autowired
    private GroupMyService groupMyService;

    @GetMapping("/delGroup")
    @ResponseBody
    public JsonResult delGroup(Integer groupId){

        String delById = groupMyService.delById(groupId);

        if ("success".equals(delById)){

            return new JsonResult(200,"删除成功");
        }

        return new JsonResult(4001,delById);
    }

    @GetMapping("/addGroup")
    @ResponseBody
    public JsonResult addGroup(GroupMy groupMy){

        String addGroup = groupMyService.addGroup(groupMy);

        if ("success".equals(addGroup)){
            return new JsonResult(200,"添加成功");
        }

        return new JsonResult(4001,addGroup);

    }

}
