package com.orchid.counselling.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 用户咨询记录
 */
public class ConsultingRecords {

    private Integer id;

    private User user;//寻找咨询用户

    private User counselor;//提供咨询人员

    private String time;//预约时间

    private String createTime;//发起咨询请求时间

    private String acceptanceTime;//处理咨询时间

    private Byte level;//情况等级

    private String description;//问题描述

    private Integer isAgree;//是否同意预约

    public Integer getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(Integer isAgree) {
        this.isAgree = isAgree;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getCounselor() {
        return counselor;
    }

    public void setCounselor(User counselor) {
        this.counselor = counselor;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAcceptanceTime() {
        return acceptanceTime;
    }

    public void setAcceptanceTime(String acceptanceTime) {
        this.acceptanceTime = acceptanceTime;
    }
}
