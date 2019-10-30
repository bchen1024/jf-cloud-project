package com.btsoft.jf.cloud.platform.security.entity;

import com.btsoft.jf.cloud.core.base.entity.impl.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 用户详情Entity
 * @author jeo_cb
 * @date 2019/9/29
 **/
public class UserDetailEntity extends BaseEntity {

    private static final long serialVersionUID = 8486772128603038418L;
    private Long userId;
    private String userSex;
    private Long userPhoto;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
