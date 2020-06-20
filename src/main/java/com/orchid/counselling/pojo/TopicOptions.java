package com.orchid.counselling.pojo;

/**
 * 试题答案
 */
public class TopicOptions {

  private Integer id;
  //试题信息
  private Topic topic;
  //答案
  private String options;

  private Double score;

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

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

  public String getOptions() {
    return options;
  }

  public void setOptions(String options) {
    this.options = options;
  }

}
