package com.btsoft.jf.cloud.platform.security.dto.user;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 用户创建DTO
 * @author jeo_cb
 * @date 2019/9/28
 **/
public class UserCreateDTO extends BaseDTO {

    private String userCn;
    private String userEn;
    private String userType;

    private String userSex;
    private Long userPhoto;

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

    public Long getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(Long userPhoto) {
        this.userPhoto = userPhoto;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
