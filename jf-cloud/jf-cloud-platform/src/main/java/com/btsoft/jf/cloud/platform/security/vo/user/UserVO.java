package com.btsoft.jf.cloud.platform.security.vo.user;

import com.btsoft.jf.cloud.core.base.vo.impl.BaseVO;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 账号VO
 * @author jeo_cb
 * @date 2019/9/28
 **/
public class UserVO extends BaseVO {

    private Long userId;
    private String userNo;
    private String userCn;
    private String userEn;
    private String mobile;
    private String mobileValidated;
    private String email;
    private String emailValidated;
    private String userType;
    private String userStatus;
    private Date passwordUpdateTime;
    private Integer passwordErrorNum;
    private Date lockTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserCn() {
        return userCn;
    }

    public void setUserCn(String userCn) {
        this.userCn = userCn;
    }

    public String getUserEn() {
        return userEn;
    }

    public void setUserEn(String userEn) {
        this.userEn = userEn;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobileValidated() {
        return mobileValidated;
    }

    public void setMobileValidated(String mobileValidated) {
        this.mobileValidated = mobileValidated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailValidated() {
        return emailValidated;
    }

    public void setEmailValidated(String emailValidated) {
        this.emailValidated = emailValidated;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Date getPasswordUpdateTime() {
        return passwordUpdateTime;
    }

    public void setPasswordUpdateTime(Date passwordUpdateTime) {
        this.passwordUpdateTime = passwordUpdateTime;
    }

    public Integer getPasswordErrorNum() {
        return passwordErrorNum;
    }

    public void setPasswordErrorNum(Integer passwordErrorNum) {
        this.passwordErrorNum = passwordErrorNum;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
