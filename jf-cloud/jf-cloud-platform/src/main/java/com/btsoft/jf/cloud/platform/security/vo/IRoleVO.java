package com.btsoft.jf.cloud.platform.security.vo;

/**
 * @author jeo_cb
 * @date 2019/10/31
 **/
public interface IRoleVO {

    /**
     * 获取角色id
     * @author jeo_cb
     * @date 2019/11/4
     * @return 角色id
     */
    Long getRoleId();

    /**
     * 设置角色名称
     * @author jeo_cb
     * @date 2019/11/4
     * @param  roleName 角色名称
     */
    void setRoleName(String roleName);

    /**
     * 设置角色名称
     * @author jeo_cb
     * @date 2019/11/4
     * @param  roleCode 角色编码
     */
    void setRoleCode(String roleCode);

    /**
     * 设置角色名称
     * @author jeo_cb
     * @date 2019/11/4
     * @param  roleDesc 角色描述
     */
    void setRoleDesc(String roleDesc);


}
