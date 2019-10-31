package com.btsoft.jf.cloud.platform.security.entity;

import com.btsoft.jf.cloud.core.base.entity.impl.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 应用用户Entity
 * @author jeo_cb
 * @date 2019/10/3
 **/
public class AppUserEntity extends BaseEntity {

    private static final long serialVersionUID = 5776919563083317086L;
    private Long id;
    private Long appId;
    private Long userId;
    private Long roleId;
    private Date beginDate;
    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
