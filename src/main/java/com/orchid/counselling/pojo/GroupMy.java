package com.orchid.counselling.pojo;

/**
 * 团体信息
 */
public class GroupMy {

  private Integer groupId;
  private String groupName;
  //管理员id，创建团体的人
  private User administrator;
  private String description;
  //所属机构
  private String affiliation;

  public Integer getGroupId() {
    return groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public User getAdministrator() {
    return administrator;
  }

  public void setAdministrator(User administrator) {
    this.administrator = administrator;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAffiliation() {
    return affiliation;
  }

  public void setAffiliation(String affiliation) {
    this.affiliation = affiliation;
  }

  public GroupMy(Integer groupId) {
    this.groupId = groupId;
  }

  public GroupMy() {
  }

  public GroupMy(String groupName) {
    this.groupName = groupName;
  }
}
