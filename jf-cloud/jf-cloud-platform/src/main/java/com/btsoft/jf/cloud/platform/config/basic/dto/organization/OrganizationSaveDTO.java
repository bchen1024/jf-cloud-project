package com.btsoft.jf.cloud.platform.config.basic.dto.organization;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 组织架构保存DTO
 * @author jeo_cb
 * @date 2019/10/18
 **/
public class OrganizationSaveDTO extends BaseDTO {

    private static final long serialVersionUID = -52702231084311258L;
    private Long orgId;
    private String orgCn;
    private String orgEn;
    private Long parentId;
    private String orgDesc;
    private Integer orgType;

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

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
