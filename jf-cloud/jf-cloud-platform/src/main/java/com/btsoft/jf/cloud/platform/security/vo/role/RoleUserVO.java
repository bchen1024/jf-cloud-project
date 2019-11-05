package com.btsoft.jf.cloud.platform.security.vo.role;

import com.btsoft.jf.cloud.core.base.vo.impl.BaseVO;
import com.btsoft.jf.cloud.core.constant.DateFormatConstants;
import com.btsoft.jf.cloud.platform.security.vo.IRoleVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 角色群组
 * @author jeo_cb
 * @date 2019/11/5
 **/
public class RoleUserVO extends BaseVO implements IRoleVO {

    private static final long serialVersionUID = -51828922458997341L;
    private Long id;
    private Long userId;
    private Long roleId;
    private String roleCode;
    private String roleName;
    private String roleDesc;
    @JsonFormat(pattern = DateFormatConstants.DATE)
    private Date beginDate;
    @JsonFormat(pattern = DateFormatConstants.DATE)
    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
