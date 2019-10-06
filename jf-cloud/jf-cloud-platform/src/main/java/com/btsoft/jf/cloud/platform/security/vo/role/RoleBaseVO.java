package com.btsoft.jf.cloud.platform.security.vo.role;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * TODO 类描述
 *
 * @author jeo_cb
 * @date 2019/10/6
 **/
public class RoleBaseVO {

    private Long roleId;
    private String roleName;
    private String roleDesc;
    private Long roleOwner;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
