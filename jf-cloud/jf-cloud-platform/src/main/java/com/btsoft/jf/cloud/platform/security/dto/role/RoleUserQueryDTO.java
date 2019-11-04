package com.btsoft.jf.cloud.platform.security.dto.role;

import com.btsoft.jf.cloud.core.base.dto.impl.SearchKeywordDTO;

/**
 * 角色用户查询DTO
 * @author jeo_cb
 * @date 2019/11/5
 **/
public class RoleUserQueryDTO extends SearchKeywordDTO {

    private Long roleId;
    private Long userId;
    private Long grantStatus;

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
}
