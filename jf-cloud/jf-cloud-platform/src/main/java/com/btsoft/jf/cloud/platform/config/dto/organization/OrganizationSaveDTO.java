package com.btsoft.jf.cloud.platform.config.dto.organization;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * TODO 类描述
 *
 * @author jeo_cb
 * @date 2019/10/18
 **/
public class OrganizationSaveDTO extends BaseDTO {

    private Long orgId;
    private String orgCn;
    private String orgEn;
    private Long parentId;
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
