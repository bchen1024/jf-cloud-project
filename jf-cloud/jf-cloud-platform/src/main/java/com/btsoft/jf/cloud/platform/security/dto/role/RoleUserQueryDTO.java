package com.btsoft.jf.cloud.platform.security.dto.role;

import com.btsoft.jf.cloud.core.base.dto.impl.SearchKeywordAppDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 角色用户查询DTO
 * @author jeo_cb
 * @date 2019/11/5
 **/
public class RoleUserQueryDTO extends SearchKeywordAppDTO {

    private static final long serialVersionUID = 1059936011029977629L;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
