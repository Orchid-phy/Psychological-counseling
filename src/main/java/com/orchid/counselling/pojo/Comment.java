package com.orchid.counselling.pojo;

import java.util.Date;

/**
 * 用户发表的动态消息
 */
public class Comment {

  private Integer id;
  private Confide confide;
  private User user;
  private String comments;
  private Date createTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Confide getConfide() {
    return confide;
  }

  public void setConfide(Confide confide) {
    this.confide = confide;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Comment() {
  }

  public Comment(Confide confide, User user, String comments) {
    this.confide = confide;
    this.user = user;
    this.comments = comments;
  }

}
