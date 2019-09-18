package com.btsoft.jf.cloud.platform.security.dto.role;

import com.btsoft.jf.cloud.core.base.dto.impl.SearchKeywordAppDTO;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 角色分页查询DTO
 * @author jeo_cb
 * @date 2019/9/5
 **/
public class RoleQueryDTO extends SearchKeywordAppDTO {

    @ApiModelProperty("角色责任人")
    private Long roleOwner;
    @ApiModelProperty("是否可申请")
    private String applyStatus;

    public Long getRoleOwner() {
        return roleOwner;
    }

    public void setRoleOwner(Long roleOwner) {
        this.roleOwner = roleOwner;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
