package com.orchid.counselling.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orchid.counselling.pojo.Comment;
import com.orchid.counselling.pojo.Confide;
import com.orchid.counselling.service.CommentService;
import com.orchid.counselling.service.ConfideService;
import com.orchid.counselling.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ConfideController {

    @Autowired
    public ConfideService confideService;

    @Autowired
    public CommentService commentService;

    /**
     * 跳转至树洞页面
     * @return
     */
    @GetMapping("/toConfide")
    public String toConfide(){
        return "confide/index.html";
    }

    /**
     * 查询所有的动态消息
     * @param pageNum
     * @return
     */
    @GetMapping("/getConfideAndComment")
    @ResponseBody
    public JsonResult getConfideAndComment(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){

        PageHelper.startPage(pageNum, 10);
        List<Confide> confides = confideService.findCommentByConId();
        PageInfo<Confide> confidePageInfo = new PageInfo<>(confides);

        return new JsonResult(200, "success", confidePageInfo);

    }

    /**
     * 根据用户id查询所有的动态消息
     * @param pageNum
     * @return
     */
    @GetMapping("/getConfideByUserId")
    @ResponseBody
    public JsonResult getConfideByUserId(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){

        PageHelper.startPage(pageNum, 10);
        List<Confide> confides = confideService.findCommentByConIdByUserId();
        PageInfo<Confide> confidePageInfo = new PageInfo<>(confides);

        return new JsonResult(200, "success", confidePageInfo);
    }

    /**
     * 添加动态评论消息
     * @return
     */
    @PostMapping("/addComment")
    @ResponseBody
    public JsonResult addComment(@RequestBody JSONObject jsonObject){

        Integer userId = jsonObject.getInteger("userId");
        Integer confideId = jsonObject.getInteger("confideId");
        String comments = jsonObject.getString("comments");

        Boolean aBoolean = commentService.addComments(userId, confideId, comments);

        if (aBoolean){
            return new JsonResult(200, "success");
        }

        return new JsonResult(4001, "error");
    }

}
