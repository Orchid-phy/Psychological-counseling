package com.orchid.counselling.vo;

import com.alibaba.fastjson.JSONObject;
import com.orchid.counselling.pojo.*;

import java.util.List;

public class ParaVo {

    private GroupMy groupMy;

    private User user;

    private Paper paper;

    private GroupPaper groupPaper;

    private EvaluationRecord evaluationRecord;

    private String para;

    private String para1;

    private String para2;

    private List list;

    private List list2;

    public GroupMy getGroupMy() {
        return groupMy;
    }

    public void setGroupMy(GroupMy groupMy) {
        this.groupMy = groupMy;
    }

    public GroupPaper getGroupPaper() {
        return groupPaper;
    }

    public void setGroupPaper(GroupPaper groupPaper) {
        this.groupPaper = groupPaper;
    }

    public List getList2() {
        return list2;
    }

    public void setList2(List list2) {
        this.list2 = list2;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getPara1() {
        return para1;
    }

    public void setPara1(String para1) {
        this.para1 = para1;
    }

    public String getPara2() {
        return para2;
    }

    public void setPara2(String para2) {
        this.para2 = para2;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public EvaluationRecord getEvaluationRecord() {
        return evaluationRecord;
    }

    public void setEvaluationRecord(EvaluationRecord evaluationRecord) {
        this.evaluationRecord = evaluationRecord;
    }
}
