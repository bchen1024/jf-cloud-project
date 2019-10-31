package com.btsoft.jf.cloud.platform.security.vo.role;

/**
 * @author jeo_cb
 * @date 2019/10/31
 **/
public interface IRoleVO {

    Long getRoleId();


    void setRoleName(String roleName);

    void setRoleDesc(String roleDesc);

    void setRoleCode(String roleCode);
}
