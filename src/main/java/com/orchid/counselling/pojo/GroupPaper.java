package com.orchid.counselling.pojo;

/**
 *
 * 给团体发布的任务
 */
public class GroupPaper {

    private Integer id;

    private GroupMy groupMy;

    private Paper paper;

    private String createTime;

    private String stopTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GroupMy getGroupMy() {
        return groupMy;
    }

    public void setGroupMy(GroupMy groupMy) {
        this.groupMy = groupMy;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public GroupPaper() {
    }

    public GroupPaper(GroupMy groupMy, Paper paper) {
        this.groupMy = groupMy;
        this.paper = paper;
    }

    @Override
    public String toString() {
        return "GroupPaper{" +
                "id=" + id +
                ", groupMy=" + groupMy +
                ", paper=" + paper +
                ", createTime='" + createTime + '\'' +
                ", stopTime='" + stopTime + '\'' +
                '}';
    }
}
