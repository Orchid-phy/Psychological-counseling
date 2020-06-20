package com.orchid.counselling.pojo;

/**
 * 申请记录
 */
public class Apply {

  private long id;
  //身份证id
  private String idCard;
  //身份证正面
  private String idCardPositive;
  //身份证背面
  private String idCardBack;
  //心理咨询师证编号
  private String licenseNumber;
  //咨询师证件照正面
  private String licenseImg;
  //申请用户
  private User applyUser;
  //所属机构
  private String mechanism;
  //是否同意
  private Integer isAgree;

  public Integer getIsAgree() {
    return isAgree;
  }

  public void setIsAgree(Integer isAgree) {
    this.isAgree = isAgree;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }


  public String getIdCardPositive() {
    return idCardPositive;
  }

  public void setIdCardPositive(String idCardPositive) {
    this.idCardPositive = idCardPositive;
  }


  public String getIdCardBack() {
    return idCardBack;
  }

  public void setIdCardBack(String idCardBack) {
    this.idCardBack = idCardBack;
  }


  public String getLicenseNumber() {
    return licenseNumber;
  }

  public void setLicenseNumber(String licenseNumber) {
    this.licenseNumber = licenseNumber;
  }


  public String getLicenseImg() {
    return licenseImg;
  }

  public void setLicenseImg(String licenseImg) {
    this.licenseImg = licenseImg;
  }

  public User getApplyUser() {
    return applyUser;
  }

  public void setApplyUser(User applyUser) {
    this.applyUser = applyUser;
  }

  public String getMechanism() {
    return mechanism;
  }

  public void setMechanism(String mechanism) {
    this.mechanism = mechanism;
  }

  public Apply() {
  }

  public Apply(String idCard, String idCardPositive, String idCardBack, User applyUser, String mechanism) {
    this.idCard = idCard;
    this.idCardPositive = idCardPositive;
    this.idCardBack = idCardBack;
    this.applyUser = applyUser;
    this.mechanism = mechanism;
  }

  public Apply(String idCard, String idCardPositive, String idCardBack, String licenseNumber, String licenseImg, User applyUser) {
    this.idCard = idCard;
    this.idCardPositive = idCardPositive;
    this.idCardBack = idCardBack;
    this.licenseNumber = licenseNumber;
    this.licenseImg = licenseImg;
    this.applyUser = applyUser;
  }
}
