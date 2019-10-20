package com.btsoft.jf.cloud.platform.config.vo.organization;

import com.btsoft.jf.cloud.core.base.vo.impl.TreeVO;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 组织架构VO
 * @author jeo_cb
 * @date 2019/10/18
 **/
public class OrganizationVO extends TreeVO<OrganizationVO> {

    private Long orgId;
    private String orgCn;
    private String orgEn;
    private Long parentId;
    private String orgDesc;
    private Long createBy;
    private Date createDate;
    private Long lastUpdateBy;
    private Date lastUpdateDate;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
