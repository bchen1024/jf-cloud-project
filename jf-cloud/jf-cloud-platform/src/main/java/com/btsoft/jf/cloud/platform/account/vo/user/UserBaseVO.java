package com.btsoft.jf.cloud.platform.account.vo.user;

import com.btsoft.jf.cloud.core.base.entity.IUserEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author jeo_cb
 * @desc 用户基本信息
 * @date 2019/9/30 14:03
 **/
public class UserBaseVO implements Serializable, IUserEntity {

    private static final long serialVersionUID = -6772020830853733444L;
    private Long userId;
    private String userNo;
    private String userCn;
    private String userEn;
    private String userType;
    private String userStatus;
    private String userSex;
    private Long userPhoto;

    @Override
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
