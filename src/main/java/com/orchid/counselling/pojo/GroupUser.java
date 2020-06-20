package com.orchid.counselling.pojo;

/**
 * 用户所属团队
 */
public class GroupUser {

  private Integer groupUserId;
  //用户
  private User user;
  //所属团队
  private GroupMy groupMy;

  public Integer getGroupUserId() {
    return groupUserId;
  }

  public void setGroupUserId(Integer groupUserId) {
    this.groupUserId = groupUserId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public GroupMy getGroupMy() {
    return groupMy;
  }

  public void setGroupMy(GroupMy groupMy) {
    this.groupMy = groupMy;
  }
}
