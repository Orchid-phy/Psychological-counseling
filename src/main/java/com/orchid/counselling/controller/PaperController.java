package com.orchid.counselling.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orchid.counselling.pojo.Paper;
import com.orchid.counselling.pojo.TopicType;
import com.orchid.counselling.service.PaperService;
import com.orchid.counselling.service.TopicService;
import com.orchid.counselling.service.TopicTypeService;
import com.orchid.counselling.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Controller
public class PaperController {

    @Autowired
    private PaperService paperService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicTypeService topicTypeService;

    private static String in_typeName;

    @GetMapping("/getPaperTop")
    @ResponseBody
    public JsonResult getPaperTop(){

        List<Paper> paperTop = paperService.findPaperTop();


        return new JsonResult(200, "success", paperTop);
    }


    @GetMapping("/getPaperByType")
    @ResponseBody
    public JsonResult getPaperByType(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                     @RequestParam(defaultValue = "健康", value="typeName") String typeName){

        in_typeName = typeName;

        //设置页面行数
        PageHelper.startPage(pageNum, 12);

        List<Paper> paperByType = paperService.findPaperByType(in_typeName);

        PageInfo<Paper> pageInfo = new PageInfo<Paper>(paperByType);

        HashMap<String, Object> map = new HashMap<>();

        map.put("pageInfo", pageInfo);
        map.put("typeName", in_typeName);

        return new JsonResult(200, "success", map);
    }

    @GetMapping("/getTopicType")
    @ResponseBody
    public JsonResult getTopicType(){

        List<TopicType> allType = topicTypeService.findAllType();

        return new JsonResult(200, "success", allType);
    }

    /**
     * 批量新增试题信息
     * @param file
     * @param paperId
     * @return
     */
    @PostMapping("/batchTopic")
    @ResponseBody
    public JsonResult batchTopic(MultipartFile file, Integer paperId){

        //新增试题信息
        String batchTopic = topicService.batchTopic(file, paperId);

        if ("success".equals(batchTopic) ){

            return new JsonResult(200, "success");
        }

        return new JsonResult(4001, "error", batchTopic);

    }

}
