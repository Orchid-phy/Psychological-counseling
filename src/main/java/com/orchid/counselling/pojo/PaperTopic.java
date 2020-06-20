package com.orchid.counselling.pojo;

/**
 * 试题和试卷关系
 */
public class PaperTopic {

  private Integer id;
  //试题
  private Topic topic;
  //试卷
  private Paper paper;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }

  public Paper getPaper() {
    return paper;
  }

  public void setPaper(Paper paper) {
    this.paper = paper;
  }
}
