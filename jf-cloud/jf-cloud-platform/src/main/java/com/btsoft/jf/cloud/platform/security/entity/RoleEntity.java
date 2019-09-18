package com.btsoft.jf.cloud.platform.security.entity;

import com.btsoft.jf.cloud.core.base.entity.impl.BaseAppEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 角色Entity
 * @author jeo_cb
 * @date 2019/8/28
 **/
public class RoleEntity extends BaseAppEntity {

    private Long roleId;
    private String roleCode;
    private String roleName;
    private String roleDesc;
    private Long roleOwner;
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

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

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
