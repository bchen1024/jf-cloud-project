package com.btsoft.jf.cloud.platform.security.dto.user;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 用户创建DTO
 * @author jeo_cb
 * @date 2019/9/28
 **/
public class UserSaveDTO extends BaseDTO {

    private Long userId;
    private String userCn;
    private String userEn;
    private String mobile;
    private String email;
    private String userType;
    private String userSex;

    //region 员工信息

    private String employeeType;
    private String employeeStatus;
    private Long employeeJob;
    private Long parentId;

    //endregion


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Long getEmployeeJob() {
        return employeeJob;
    }

    public void setEmployeeJob(Long employeeJob) {
        this.employeeJob = employeeJob;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
