package com.btsoft.jf.cloud.core.base.entity.impl;

import com.btsoft.jf.cloud.core.base.entity.IBaseEntity;
import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 实体基类
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class BaseEntity implements IBaseEntity {

    private String enableFlag;
    private Long createBy;
    private Date createDate;
    private Long lastUpdateBy;
    private Date lastUpdateDate;

    @Override
    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    @Override
    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Long getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(Long lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    @Override
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    @Override
    public Long getCurrentUserId() {
        return JfCloud.getCurrent().getCurrentUserId();
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
