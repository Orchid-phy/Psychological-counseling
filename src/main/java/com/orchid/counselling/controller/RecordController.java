package com.orchid.counselling.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orchid.counselling.pojo.*;
import com.orchid.counselling.service.ConsultingRecordsService;
import com.orchid.counselling.service.EvaluationRecordService;
import com.orchid.counselling.service.GroupPaperService;
import com.orchid.counselling.util.JsonResult;
import com.orchid.counselling.vo.ParaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RecordController {

    @Autowired
    private ConsultingRecordsService consultingRecordsService;

    @Autowired
    private EvaluationRecordService evaluationRecordService;

    @Autowired
    private GroupPaperService groupPaperService;

    //查询参数
    private static String in_search="";

    //测试查询参数
    private static String inTestSearch="";

    private static Integer getTaskGroupId;

    private static Integer getTaskPaperId;


    /**
     * 分页查询所有预约
     * @param model
     * @param pageNum
     * @param search
     * @return
     */
    @GetMapping("/getAllCounsellingRecord")
    public String getAllCounsellingRecord(Model model,
                                          @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                          @RequestParam(defaultValue = "", value = "search", required = false) String search,
                                            @RequestParam(defaultValue = "", value = "condition", required = false) String condition){

        //判断保存的查询条件个输入的是否一致,不一致就覆盖
        if (!"".equals(condition) ){
            in_search = search;
        }

        model.addAttribute("counsellingSearch", in_search);

        PageHelper.startPage(pageNum, 4);
        //查询数据
        List<ConsultingRecords> list = consultingRecordsService.findAllInfoByUsername(in_search);
        PageInfo<ConsultingRecords> pageInfo = new PageInfo<ConsultingRecords>(list);
        model.addAttribute("pageInfo", pageInfo);

        return "/self/counselingrecore";
    }

    /**
     * 查询所有未来时间的预约
     * @param model
     * @return
     */
    @GetMapping("/getNewCounsellingRecord")
    public String getNewCounsellingRecord(Model model){

        //获得预约记录
        Map<String, List<ConsultingRecords>> recordsListMap = consultingRecordsService.selConsultingReByName();

        model.addAttribute("recordsListMap", recordsListMap);

        return "/self/myappointment";
    }

    /**
     * 查询所有未来时间的预约
     * @param model
     * @return
     */
    @GetMapping("/getCounselorNewCounsellingRecord")
    public String getCounselorNewCounsellingRecord(Model model){

        //获得预约记录
        Map<String, List<ConsultingRecords>> recordsListMap = consultingRecordsService.selCounselorConsultingReByName();

        model.addAttribute("recordsListMap", recordsListMap);

        return "/self/counselorappointment";
    }

    /**
     * 取消预约
     * @param id
     * @return
     */
    @GetMapping("/delRecord")
    @ResponseBody
    public JsonResult delRecord(Integer id){

        Boolean isDel = consultingRecordsService.delRecord(id);

        if (isDel){
            return new JsonResult(200, "success");
        }

        return new JsonResult(4001,"取消失败");
    }

    /**
     * 查询未来时间的测试
     * @param model
     * @return
     */
    @GetMapping("/getNewTestRecord")
    public String getNewTestRecord(Model model){

        //获得预约记录
        Map<String, List<EvaluationRecord>> testRecordsMap = evaluationRecordService.selTestReByName();

        model.addAttribute("testRecordsMap", testRecordsMap);

        return "/self/mytest";
    }

    /**
     * 分页查询所有测试记录
     * @param model
     * @param pageNum
     * @param search
     * @return
     */
    @GetMapping("/getAllTestRecord")
    public String getAllTestRecord(Model model,
                                  @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                  @RequestParam(defaultValue = "", value = "search", required = false) String search,
                               @RequestParam(defaultValue = "", value = "condition", required = false) String condition){

        //判断保存的查询条件个输入的是否一致,不一致就覆盖
        if (!"".equals(condition)){
            inTestSearch = search;
        }

        model.addAttribute("testSearch", inTestSearch);

        PageHelper.startPage(pageNum, 4);
        List<EvaluationRecord> list = evaluationRecordService.findAllTestByUsername(inTestSearch);
        PageInfo<EvaluationRecord> pageInfo = new PageInfo<EvaluationRecord>(list);
        model.addAttribute("pageInfo", pageInfo);

        return "/self/testrecord";
    }

    /**
     * 发布任务
     * @return
     */
    @PostMapping("/postTask")
    @ResponseBody
    public JsonResult postTask(@RequestBody String params){

        //解析前端数据
        JSONObject jsonObject = JSONObject.parseObject(params);
        String param = jsonObject.getString("params");
        JSONObject object = JSONObject.parseObject(param);
        String groups = object.getString("groups");
        String papers = object.getString("papers");
        String stopDate = object.getString("stopDate");
        JSONArray jsonArrayGroups = JSONArray.parseArray(groups);
        JSONArray jsonArrayPapers = JSONArray.parseArray(papers);
        List<Integer> groupsList = new ArrayList<>();
        for (int i = 0; i < jsonArrayGroups.size(); i++) {
            groupsList.add(i,jsonArrayGroups.getInteger(i));
        }

        List<Integer> papersList = new ArrayList<>();
        for (int i = 0; i < jsonArrayPapers.size(); i++) {
            papersList.add(i,jsonArrayPapers.getInteger(i));
        }

        //插入任务信息
        String evaluation = evaluationRecordService.postEvaluation(groupsList, papersList, stopDate);

        if ("success".equals(evaluation)){
            return new JsonResult(200, "发布成功");
        }

        return new JsonResult(4001, "evaluation");
    }


    @GetMapping("/getTask")
    @ResponseBody
    public JsonResult getTask(
                        @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                        @RequestParam(defaultValue = "", value = "groupId", required = false) Integer groupId,
                        @RequestParam(defaultValue = "", value = "paperId", required = false) Integer paperId){


        //判断保存的查询条件个输入的是否一致,不一致就覆盖
        if (!"".equals(groupId) ){
            getTaskGroupId = groupId;
        }
        if (!"".equals(getTaskPaperId)){
            getTaskPaperId = paperId;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("groupId", getTaskGroupId);
        map.put("paperId", getTaskPaperId);

        PageHelper.startPage(pageNum, 6);
        //查询数据
        List<GroupPaper> allTaskByAdmin = groupPaperService.getAllTaskByAdmin(groupId, paperId);
        //将查询到的信息保存
        PageInfo<GroupPaper> taskPageInfo = new PageInfo<GroupPaper>(allTaskByAdmin);

        map.put("taskPageInfo", taskPageInfo);

        return new JsonResult(200, "success",map);
    }

    /**
     * 用户点击预约，新增预约记录
     * @return
     */
    @GetMapping("/addConsultingRecords")
    @ResponseBody
    public JsonResult addConsultingRecords(String time, Integer counselorId){

        //构造参数
        ConsultingRecords consultingRecords = new ConsultingRecords();
        consultingRecords.setTime(time);
        User user = new User();
        user.setUserId(counselorId);
        consultingRecords.setCounselor(user);

        //新增用户记录
        Boolean addRecord = consultingRecordsService.addEvaluationRecord(consultingRecords);

        if (addRecord){
            return new JsonResult(200, "success");
        }

        return new JsonResult(4001, "预约失败，请再次预约。");

    }


    /**
     * 根据用户名查询咨询记录
     * @param jsonObject
     * @return
     */
    @PostMapping("/getConsultingRecordByUserId")
    @ResponseBody
    public JsonResult getConsultingRecordByUserId(@RequestBody JSONObject jsonObject){

        //用户名
        String username = jsonObject.getString("username");

        //第几页
        Integer pageNum = jsonObject.getInteger("pageNum");
        if (pageNum == null || "".equals(pageNum)){
            pageNum = 1;
        }

        //分页条件
        PageHelper.startPage(pageNum, 8);

        //查询数据
        List<ConsultingRecords> records = consultingRecordsService.findConsultingRecordByUserId(username);

        //处理分页的数据
        PageInfo<ConsultingRecords> userRecordsPageInfo = new PageInfo<>(records);

        Map<String, Object> map = new HashMap<>();
        map.put("userRecordsPageInfo", userRecordsPageInfo);
        map.put("username", username);

        return new JsonResult(200, "success", map);
    }

    /**
     * 根据用户名查询测试记录
     * @return
     */
    @PostMapping("/getEvaRecordByUsername")
    @ResponseBody
    public JsonResult getEvaRecordByUsername(@RequestBody JSONObject jsonObject){

        //用户名
        String username = jsonObject.getString("username");

        //第几页
        Integer pageNum = jsonObject.getInteger("pageNum");
        if (pageNum == null || "".equals(pageNum)){
            pageNum = 1;
        }

        //分页条件
        PageHelper.startPage(pageNum, 8);

        //查询数据
        List<EvaluationRecord> records = evaluationRecordService.findEvaRecordByUsername(username);

        //处理分页的数据
        PageInfo<EvaluationRecord> userEvaRecordsPageInfo = new PageInfo<>(records);

        Map<String, Object> map = new HashMap<>();
        map.put("userEvaRecordsPageInfo", userEvaRecordsPageInfo);
        map.put("username", username);

        return new JsonResult(200, "success", map);
    }

}
