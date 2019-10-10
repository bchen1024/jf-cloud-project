package com.btsoft.jf.cloud.platform.security.dto.permission;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseAppDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 权限查询DTO
 * @author jeo_cb
 * @date 2019/10/7
 **/
public class PermissionQueryDTO extends BaseAppDTO {

    private String queryType;
    private Long roleId;

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
