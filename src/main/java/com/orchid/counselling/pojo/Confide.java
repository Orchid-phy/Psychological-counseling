package com.orchid.counselling.pojo;

import java.util.Date;
import java.util.List;

/**
 * 动态消息的评论
 */
public class Confide {

  private long id;
  private String message;
  private Date createTime;
  private String img;
  private User user;
  private List<Comment> commentList;

  public List<Comment> getCommentList() {
    return commentList;
  }

  public void setCommentList(List<Comment> commentList) {
    this.commentList = commentList;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
