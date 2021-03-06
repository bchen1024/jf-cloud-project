package com.btsoft.jf.cloud.platform.config.basic.entity;

import com.btsoft.jf.cloud.core.base.entity.impl.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 组织机构Entity
 * @author jeo_cb
 * @date 2019/10/2
 **/
public class OrganizationEntity extends BaseEntity {

    private Long orgId;
    private String orgCn;
    private String orgEn;
    private Long parentId;
    private Integer orgType;
    private String orgDesc;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgCn() {
        return orgCn;
    }

    public void setOrgCn(String orgCn) {
        this.orgCn = orgCn;
    }

    public String getOrgEn() {
        return orgEn;
    }

    public void setOrgEn(String orgEn) {
        this.orgEn = orgEn;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
