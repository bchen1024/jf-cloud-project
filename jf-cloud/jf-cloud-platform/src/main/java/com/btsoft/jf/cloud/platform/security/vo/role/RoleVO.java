package com.btsoft.jf.cloud.platform.security.vo.role;

import com.btsoft.jf.cloud.core.base.vo.impl.BaseAppVO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 角色VO
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class RoleVO extends BaseAppVO {

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
