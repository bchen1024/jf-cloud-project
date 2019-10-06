package com.btsoft.jf.cloud.platform.security.dto.permission;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseAppDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 权限同步DTO
 * @author jeo_cb
 * @date 2019/10/6
 **/
public class PermissionSyncDTO extends BaseAppDTO {

    private String contextPath;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
