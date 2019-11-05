package com.btsoft.jf.cloud.platform.security.vo.group;

import com.btsoft.jf.cloud.core.base.vo.impl.BaseVO;
import com.btsoft.jf.cloud.platform.security.vo.IGroupVO;
import com.btsoft.jf.cloud.platform.security.vo.IRoleVO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 群组角色VO
 * @author jeo_cb
 * @date 2019/10/31 16:56
 **/
public class GroupRoleVO extends BaseVO  implements IRoleVO, IGroupVO {
    private static final long serialVersionUID = -6872538825801556078L;

    private Long id;
    private Long groupId;
    private String groupCode;
    private String groupName;
    private String groupDesc;
    private Long roleId;
    private String roleCode;
    private String roleName;
    private String roleDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    @Override
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    @Override
    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    @Override
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    @Override
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }
    @Override
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    @Override
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
