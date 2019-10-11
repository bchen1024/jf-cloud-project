package com.btsoft.jf.cloud.platform.security.vo.permission;

import com.btsoft.jf.cloud.core.base.vo.impl.TreeVO;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 权限点VO
 * @author jeo_cb
 * @date 2019/10/7
 **/
public class PermissionVO extends TreeVO<PermissionVO> {
    private Long permissionId;
    private String permissionCode;
    private String permissionDescCn;
    private String permissionDescEn;
    private String parentCode;
    private String permissionType;
    private String permissionSource;
    private Long createBy;
    private Date createDate;
    private Long lastUpdateBy;
    private Date lastUpdateDate;
    private String enableFlag;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionDescCn() {
        return permissionDescCn;
    }

    public void setPermissionDescCn(String permissionDescCn) {
        this.permissionDescCn = permissionDescCn;
    }

    public String getPermissionDescEn() {
        return permissionDescEn;
    }

    public void setPermissionDescEn(String permissionDescEn) {
        this.permissionDescEn = permissionDescEn;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public String getPermissionSource() {
        return permissionSource;
    }

    public void setPermissionSource(String permissionSource) {
        this.permissionSource = permissionSource;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(Long lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
