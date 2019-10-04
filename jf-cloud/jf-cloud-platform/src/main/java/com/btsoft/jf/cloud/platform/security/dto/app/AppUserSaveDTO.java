package com.btsoft.jf.cloud.platform.security.dto.app;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseDTO;

/**
 * 应用用户保存DTO
 * @author jeo_cb
 * @date 2019/10/4
 **/
public class AppUserSaveDTO extends BaseDTO {

    private Long appId;
    private Long userId;
    private Long roleId;

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
}
