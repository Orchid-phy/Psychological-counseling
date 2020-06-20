package com.orchid.counselling.pojo;

/**
 * 测试卷子
 */
public class Paper {

  private Integer id;
  private String paperName;
  //类型
  private TopicType type;
  private String description;

  private String img;

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  //使用此测试次数
  private Long numbers;

  public Long getNumbers() {
    return numbers;
  }

  public void setNumbers(Long numbers) {
    this.numbers = numbers;
  }

  public String getPaperName() {
    return paperName;
  }

  public void setPaperName(String paperName) {
    this.paperName = paperName;
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public TopicType getType() {
    return type;
  }

  public void setType(TopicType type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Paper(Integer id) {
    this.id = id;
  }

  public Paper() {
  }
}
