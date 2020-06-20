package com.orchid.counselling.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orchid.counselling.pojo.Apply;
import com.orchid.counselling.service.ApplyService;
import com.orchid.counselling.service.FileUpAndDownService;
import com.orchid.counselling.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.jnlp.FileSaveService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ApplyController {

    @Autowired
    private FileUpAndDownService fileUpAndDownService;

    @Autowired
    private ApplyService applyService;

    /**
     * 申请为管理员
     * @param mechanism
     * @param IDCard
     * @param inputfile1
     * @param inputfile2
     * @return
     */
    @PostMapping("/adminApply")
    @ResponseBody
    public JsonResult adminApply(String mechanism, String IDCard, MultipartFile inputfile1, MultipartFile inputfile2){

        //文件保存路径
        String savePath = "D:/ideaFile/counselling/src/main/resources/static/updateFile/certificate";
        //将文件保存至目录中
        String s1 = fileUpAndDownService.uploadPicture(inputfile1, savePath);
        String s2 = fileUpAndDownService.uploadPicture(inputfile2, savePath);

        //添加申请记录
        boolean apply = applyService.adminApply(mechanism, IDCard, s1, s2);

        if (apply){
            return new JsonResult(200, "success");
        }

        return new JsonResult(4001, "success");
    }

    /**
     * 申请为咨询师
     * @param IDCard
     * @param IDNum
     * @param inputfile1
     * @param inputfile2
     * @param inputfile3
     * @return
     */
    @PostMapping("/councellingApply")
    public String councellingApply(String IDCard, String IDNum, MultipartFile inputfile1, MultipartFile inputfile2, MultipartFile inputfile3){

        //文件保存路径
        String savePath = "D:/ideaFile/counselling/src/main/resources/static/updateFile/certificate";
        //将文件保存至目录中
        String s1 = fileUpAndDownService.uploadPicture(inputfile1, savePath);
        String s2 = fileUpAndDownService.uploadPicture(inputfile2, savePath);
        String s3 = fileUpAndDownService.uploadPicture(inputfile3, savePath);

        Boolean aBoolean = applyService.addCouncellingApply(IDCard, IDNum, s1, s2, s3);

        if (aBoolean){
            return "self/index";
        }

        return "self/counsellingapply";
    }

    /**
     * 查看管理员申请记录
     * @return
     */
    @PostMapping("/getApply")
    @ResponseBody
    public JsonResult getApply(@RequestBody JSONObject jsonObject){

        Long applyUserId = jsonObject.getLong("applyUserId");
        Integer pageNum = jsonObject.getInteger("pageNum");

        if (pageNum == null){
            pageNum = 1;
        }

        PageHelper.startPage(pageNum, 5);

        List<Apply> applyList = applyService.findApply(applyUserId);

        PageInfo<Apply> applyPageInfo = new PageInfo(applyList);

        return new JsonResult(200, "success", applyPageInfo);
    }

    /**
     * 查看管理员申请记录
     * @return
     */
    @PostMapping("/getCounselorApply")
    @ResponseBody
    public JsonResult getCounselorApply(@RequestBody JSONObject jsonObject){

        Long applyUserId = jsonObject.getLong("applyUserId");
        Integer pageNum = jsonObject.getInteger("pageNum");

        if (pageNum == null){
            pageNum = 1;
        }

        PageHelper.startPage(pageNum, 5);

        List<Apply> applyList = applyService.findCounselorApply(applyUserId);

        PageInfo<Apply> applyPageInfo = new PageInfo(applyList);

        return new JsonResult(200, "success", applyPageInfo);
    }


}
