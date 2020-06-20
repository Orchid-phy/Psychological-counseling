package com.orchid.counselling.pojo;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class User {

  private long userId;
  @NotBlank(message = "{username.notBlank}")
  private String username;
  @NotBlank(message = "{realName.notBlank}")
  private String realName;
  private String password;
  private String salt;
  private String idCard;
  @NotBlank(message = "{phone.notBlank}")
  private String phone;
  private String email;
  private String sex;
  private Integer age;
  private GroupMy group;
  private Role role;
  private Date createTime;
  //用户头像
  private String img;

  /**
   * 记住我
   */
  private boolean rememberMe;

  //验证码
  private String validCode;

  public String getValidCode() {
    return validCode;
  }

  public void setValidCode(String validCode) {
    this.validCode = validCode;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isRememberMe() {
    return rememberMe;
  }

  public void setRememberMe(boolean rememberMe) {
    this.rememberMe = rememberMe;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public GroupMy getGroup() {
    return group;
  }

  public void setGroup(GroupMy group) {
    this.group = group;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public User() {
  }

  public User(String username) {
    this.username = username;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }
}
