package com.btsoft.jf.cloud.platform.account.dto.user;

import com.btsoft.jf.cloud.core.base.dto.impl.SearchKeywordAppDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 账号查询DTO
 * @author jeo_cb
 * @date 2019/9/28
 **/
public class UserQueryDTO extends SearchKeywordAppDTO {

    private static final long serialVersionUID = -2064446228018611089L;
    private String userType;
    private String userStatus;
    private String mobileValidated;
    private String emailValidated;

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

    public String getMobileValidated() {
        return mobileValidated;
    }

    public void setMobileValidated(String mobileValidated) {
        this.mobileValidated = mobileValidated;
    }

    public String getEmailValidated() {
        return emailValidated;
    }

    public void setEmailValidated(String emailValidated) {
        this.emailValidated = emailValidated;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
