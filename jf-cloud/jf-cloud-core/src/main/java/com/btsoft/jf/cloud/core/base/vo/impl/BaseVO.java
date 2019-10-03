package com.btsoft.jf.cloud.core.base.vo.impl;

import com.btsoft.jf.cloud.core.base.vo.IBaseVO;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 基类VO
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class BaseVO implements IBaseVO {

    private Long createBy;
    private Date createDate;
    private Long lastUpdateBy;
    private Date lastUpdateDate;

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
