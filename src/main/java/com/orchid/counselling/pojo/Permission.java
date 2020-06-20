package com.orchid.counselling.pojo;

import java.util.Date;

/**
 * 权限
 */
public class Permission {

  private Integer permissionId;//权限id
  private String permName;//权限名字
  private String url;//权限路径
  private Byte type;//类型
  private Integer parentId;//上级目录
  private String icon;//图标
  private Date createTime;//创建时间
  private String description;//描述


  public Integer getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(Integer permissionId) {
    this.permissionId = permissionId;
  }

  public String getPermName() {
    return permName;
  }

  public void setPermName(String permName) {
    this.permName = permName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Byte getType() {
    return type;
  }

  public void setType(Byte type) {
    this.type = type;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
