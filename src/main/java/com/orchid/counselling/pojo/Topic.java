package com.orchid.counselling.pojo;

import java.util.List;

/**
 * 试题
 */
public class Topic {

  private Integer id;
  //问题
  private String testQuestion;
  //描述
  private String description;

  private List<TopicOptions> optionsList;

  public List<TopicOptions> getOptionsList() {
    return optionsList;
  }

  public void setOptionsList(List<TopicOptions> optionsList) {
    this.optionsList = optionsList;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTestQuestion() {
    return testQuestion;
  }

  public void setTestQuestion(String testQuestion) {
    this.testQuestion = testQuestion;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
