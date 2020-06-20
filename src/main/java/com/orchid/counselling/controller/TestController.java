package com.orchid.counselling.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orchid.counselling.pojo.*;
import com.orchid.counselling.service.*;
import com.orchid.counselling.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private GroupMyService groupMyService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private FileUpAndDownService fileUpAndDownService;

    @Autowired
    private PaperResultService paperResultService;

    @Autowired
    private EvaluationRecordService evaluationRecordService;

    //查询团队条件  id
    private static String in_searchId;

    //查询团队条件  团队名字
    private static String in_searchGroupName;

    //查询团队条件  id
    private static String in_searchId_admin;

    //查询团队条件  团队名字
    private static String in_searchGroupName_admin;

    //查询试题id
    private static String in_paperId;

    //查询试题条件，类型名
    private static String in_typeName;

    //查询题目的试题参数
    private static Integer in_paperTopicId;

    //查询试题信息参数
    private static Integer in_topicId;

    //记录 用户测试时，选择的试题id
    private static Integer in_test_paperId;

    //记录用户选中试题的详细信息
    private Paper paper = null;

    //保存用户的任务id
    private static Integer in_record_id = 0;

    /**
     * 查询团体信息
     * @param pageNum
     * @param searchId
     * @param searchGroupName
     * @return
     */
    @GetMapping("/getGroup")
    @ResponseBody
    public JsonResult getGroup(
                             @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                           @RequestParam(defaultValue = "", value = "searchId", required = false) String searchId,
                           @RequestParam(defaultValue = "", value = "searchGroupName", required = false) String searchGroupName){

        //判断保存的查询条件个输入的是否一致,不一致就覆盖
        if (!"".equals(searchId)){
            in_searchId = searchId;
        }
        if (!"".equals(searchGroupName)){
            in_searchGroupName = searchGroupName;
        }

        Map<String, Object> groupMap = new HashMap<>();
        groupMap.put("searchId", in_searchId);
        groupMap.put("searchGroupName", in_searchGroupName);

        PageHelper.startPage(pageNum, 8);
        //查询数据
        List<GroupMy> group = groupMyService.findAllGroupByCounselor(in_searchId, in_searchGroupName);

        //将查询到的信息保存
        PageInfo<GroupMy> pageInfo = new PageInfo<GroupMy>(group);

        List<GroupMy> list = pageInfo.getList();
        groupMap.put("list",list);
        groupMap.put("pageInfo", pageInfo);

        return new JsonResult(200, "success",groupMap);
    }

    /**
     * 查询团体信息
     * @param pageNum
     * @param searchId
     * @param searchGroupName
     * @return
     */
    @GetMapping("/getGroupByAdmin")
    @ResponseBody
    public JsonResult getGroupByAdmin(
            @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
            @RequestParam(defaultValue = "", value = "searchId", required = false) String searchId,
            @RequestParam(defaultValue = "", value = "searchGroupName", required = false) String searchGroupName){

        //判断保存的查询条件个输入的是否一致,不一致就覆盖
        if (!"".equals(searchId) && searchId != null){
            in_searchId_admin = searchId;
        }
        if (!"".equals(searchGroupName) && searchGroupName != null){
            in_searchGroupName_admin = searchGroupName;
        }

        Map<String, Object> groupMap = new HashMap<>();
        groupMap.put("searchId", in_searchId_admin);
        groupMap.put("searchGroupName", in_searchGroupName_admin);

        PageHelper.startPage(pageNum, 8);
        //查询数据
        List<GroupMy> group = groupMyService.findAllGroupByAdmin(in_searchId_admin, in_searchGroupName_admin);

        //将查询到的信息保存
        PageInfo<GroupMy> pageInfo = new PageInfo<GroupMy>(group);

        List<GroupMy> list = pageInfo.getList();
        groupMap.put("list",list);
        groupMap.put("pageInfo", pageInfo);

        return new JsonResult(200, "success", groupMap);
    }

    /**
     * 查询试卷信息
     * @param pageNum
     * @param paperId
     * @param typeName
     * @return
     */
    @GetMapping("/getPaper")
    @ResponseBody
    public JsonResult getPaper(
                               @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                               @RequestParam(defaultValue = "", value = "paperId", required = false) String paperId,
                               @RequestParam(defaultValue = "", value = "typeName", required = false) String typeName){

        //判断保存的查询条件个输入的是否一致,不一致就覆盖
        if (!"".equals(paperId) ){
            in_paperId = paperId;
        }
        if (!"".equals(typeName)){
            in_typeName = typeName;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("paperId", in_paperId);
        map.put("typeName", in_typeName);

        PageHelper.startPage(pageNum, 8);
        //查询数据
        List<Paper> paper = paperService.findAllPaperByIdOrType(in_paperId, in_typeName);
        //将查询到的信息保存
        PageInfo<Paper> paperPageInfo = new PageInfo<Paper>(paper);

        //获取数据
        List<Paper> list = paperPageInfo.getList();
        map.put("paperPageInfo", paperPageInfo);
        map.put("list", list);

        return new JsonResult(200, "success",map);
    }


    /**
     * 根据试题id查询所有题目信息
     * @param pageNum
     * @param paperId
     * @return
     */
    @GetMapping("/getTopicByPaperId")
    @ResponseBody
    public JsonResult getTopicByPaperId( @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                         @RequestParam(defaultValue = "8", value = "pageSize") Integer pageSize,
                                         @RequestParam(defaultValue = "", value = "paperId", required=false) Integer paperId){

        //如果有paperId 保存
        if (!"".equals(paperId)){
            in_paperTopicId = paperId;
        }

        PageHelper.startPage(pageNum, pageSize);
        //查询数据
        List<Topic> topic = null;

        //如果是用户选择测试试题，则根据用户选择的试题id查询
        if (!"".equals(in_test_paperId) && in_test_paperId != null){
            topic = topicService.findAllTopicByPaperId(in_test_paperId);
        }else {
            topic = topicService.findAllTopicByPaperId(in_paperTopicId);
        }

        //将查询到的信息保存
        PageInfo<Topic> topicPageInfo = new PageInfo<Topic>(topic);

        if (topic.size() == 0){
            return new JsonResult(4001, "未查询到数据");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("topicPageInfo", topicPageInfo);
        map.put("paper", this.paper);

        return new JsonResult(200, "success", map);
    }

    /**
     * 根据试题id查询试题信息
     * @param pageNum
     * @param topicId
     * @return
     */
    @GetMapping("/getTopicByTopicId")
    @ResponseBody
    public JsonResult getTopicByTopicId(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                        @RequestParam(defaultValue = "", value = "topicId", required=false) Integer topicId){


        if (!"".equals(topicId)){
            in_topicId = topicId;
        }

        PageHelper.startPage(pageNum, 8);
        //查询数据
        List<Topic> topic = topicService.findTopicByTopicId(in_topicId);

        //将查询到的信息保存
        PageInfo<Topic> topicPageInfo = new PageInfo<Topic>(topic);

        if (topic.size() == 0){
            return new JsonResult(4001, "未查询到数据");
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("topicPageInfo",topicPageInfo);
        map.put("topicId",String.valueOf(in_topicId));

        return new JsonResult(200, "success", map);

    }

//    @PostMapping("/addImg")
//    public String addImg(MultipartFile file){
//
//        //文件保存路径
//        String savePath = "D:/ideaFile/counselling/src/main/resources/static/updateFile/img";
//
//        String s = fileUpAndDownService.uploadPicture(file, savePath);
//
//        return s;
//    }


    /**
     * 跳转至测试页面，并将试题id传回前端
     * @param paperId
     * @return
     */
    @GetMapping("/test")
    public String test(Integer paperId, Integer recordId){

        //保存用户选中的测试题id
        this.in_test_paperId = paperId;
        //保存用户的任务id
        this.in_record_id = recordId;

        //查询试题的详细信息
        this.paper = paperService.findPaperById(in_test_paperId);

        return "home/test";
    }

    /**
     * 测试结束，查看结果，并记录
     * @param paperId
     * @return
     */
    @GetMapping("/getPaperResult")
    @ResponseBody
    public JsonResult getPaperResult(Integer paperId, Double score){

        //查询测试结果
        List<PaperResult> results = paperResultService.findResultByPaperId(paperId);

        //如果为测试任务，则记录测试的结果
        if (this.in_record_id != null && this.in_record_id != 0){
            EvaluationRecord evaluationRecord = new EvaluationRecord(this.in_record_id, score);
            evaluationRecordService.upScore(evaluationRecord);
        }


        return new JsonResult(200, "success", results);
    }

}
