package com.orchid.counselling.pojo;

/**
 * 试题结果
 */
public class PaperResult {

    private Integer id;

    private Paper paper;

    //结果分数字段
    private String scoreInterval;
    //分数对应的结果分析
    private String result;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public String getScoreInterval() {
        return scoreInterval;
    }

    public void setScoreInterval(String scoreInterval) {
        this.scoreInterval = scoreInterval;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
