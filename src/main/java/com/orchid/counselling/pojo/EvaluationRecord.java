package com.orchid.counselling.pojo;

/**
 * 测评结果记录
 */
public class EvaluationRecord {

    private Integer id;

    private User user;//参与测评人员

    private double score;//测试结果分数

    private Paper paper;//试题id

    private String releaseDate;//发布日期

    private String stopDate;//截止日期

    private User releaseUser;//发布人

    public User getReleaseUser() {
        return releaseUser;
    }

    public void setReleaseUser(User releaseUser) {
        this.releaseUser = releaseUser;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
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

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public EvaluationRecord() {
    }

    public EvaluationRecord(Integer id, double score) {
        this.id = id;
        this.score = score;
    }
}

