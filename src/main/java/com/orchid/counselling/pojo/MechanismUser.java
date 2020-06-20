package com.orchid.counselling.pojo;

/**
 * 用户所属机构
 */
public class MechanismUser {

  private Integer id;
  //所属机构
  private Mechanism mechanism;
  //用户
  private User user;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Mechanism getMechanism() {
    return mechanism;
  }

  public void setMechanism(Mechanism mechanism) {
    this.mechanism = mechanism;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
