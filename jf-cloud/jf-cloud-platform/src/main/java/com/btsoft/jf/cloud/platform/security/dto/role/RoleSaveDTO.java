package com.btsoft.jf.cloud.platform.security.dto.role;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseAppDTO;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 角色包存DTO
 * @author jeo_cb
 * @date 2019/9/1
 **/
public class RoleSaveDTO extends BaseAppDTO {

    @ApiModelProperty("角色id,更新时非空")
    private Long roleId;
    @ApiModelProperty("角色编码，同应用下不能重复")
    private String roleCode;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色责任人id")
    private Long roleOwner;
    @ApiModelProperty("角色描述")
    private String roleDesc;
    @ApiModelProperty("是否可申请，Y|N")
    private String applyStatus;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getRoleOwner() {
        return roleOwner;
    }

    public void setRoleOwner(Long roleOwner) {
        this.roleOwner = roleOwner;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
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
