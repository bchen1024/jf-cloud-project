package com.btsoft.jf.cloud.platform.security.vo.app;

import com.btsoft.jf.cloud.core.base.vo.impl.BaseVO;
import com.btsoft.jf.cloud.core.constant.DateFormatConstants;
import com.btsoft.jf.cloud.platform.security.vo.IAppVO;
import com.btsoft.jf.cloud.platform.security.vo.IRoleVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 应用用户VO
 * @author jeo_cb
 * @date 2019/10/3
 **/
public class AppUserVO extends BaseVO implements IRoleVO, IAppVO {

    private static final long serialVersionUID = 7418160838626002613L;
    private Long id;
    private Long appId;
    private String appCode;
    private String appName;
    private String appDesc;
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

    @Override
    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppCode() {
        return appCode;
    }

    @Override
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    @Override
    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDesc() {
        return appDesc;
    }

    @Override
    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
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
