package com.btsoft.jf.cloud.platform.security.dto.app;

import com.btsoft.jf.cloud.core.base.dto.impl.SearchKeywordDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 应用用户查询DTO
 * @author jeo_cb
 * @date 2019/10/3
 **/
public class AppUserQueryDTO extends SearchKeywordDTO {

    private static final long serialVersionUID = 6616628906288699364L;
    private Long appId;
    private Long roleId;
    private Long userId;
    private Long grantStatus;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGrantStatus() {
        return grantStatus;
    }

    public void setGrantStatus(Long grantStatus) {
        this.grantStatus = grantStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
