package com.btsoft.jf.cloud.platform.security.entity;

import com.btsoft.jf.cloud.core.base.entity.impl.BaseAppEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 权限点Entity
 * @author jeo_cb
 * @date 2019/10/6
 **/
public class PermissionEntity extends BaseAppEntity {

    private Long permissionId;
    private String permissionCode;
    private String permissionDescCn;
    private String permissionDescEn;
    private String parentCode;
    private String permissionType;
    private String permissionSource;
    private Integer permissionSort;
    private String appSource;

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

    public Integer getPermissionSort() {
        return permissionSort;
    }

    public void setPermissionSort(Integer permissionSort) {
        this.permissionSort = permissionSort;
    }

    public String getAppSource() {
        return appSource;
    }

    public void setAppSource(String appSource) {
        this.appSource = appSource;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
