package com.btsoft.jf.core.mapper.entity.impl;

import com.btsoft.jf.core.context.impl.JfSupport;
import com.btsoft.jf.core.mapper.annotation.Column;
import com.btsoft.jf.core.mapper.annotation.Id;
import com.btsoft.jf.core.mapper.entity.IBaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 基类实体
 * @author jeo_cb
 * @date 2020/1/12
 **/
public class BaseEntity<ID extends Serializable> implements IBaseEntity<ID> {

    private static final long serialVersionUID = 3025308458431582470L;

    @Column
    @Id
    private ID id;
    @Column
    private Integer status;
    @Column(name = "create_by")
    private Long createBy;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "last_update_by")
    private Long lastUpdateBy;
    @Column(name = "last_update_date")
    private Date lastUpdateDate;
    @Column
    private String remark;

    @Override
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public Long getCurrentUserId() {
        return JfSupport.getCurrent(true).getCurrentUserId();
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
