package com.btsoft.jf.cloud.platform.account.dto.user;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 用户选择查询DTO
 * @author jeo_cb
 * @date 2019/10/31
 **/
public class UserSelectQueryDTO implements Serializable {

    private static final long serialVersionUID = 282166486544544820L;
    private String keyword;
    private String userType;
    private String userStatus;
    private String userSex;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
