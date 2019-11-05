package com.btsoft.jf.cloud.platform.security.entity;

import com.btsoft.jf.cloud.core.base.entity.impl.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 常用人员Entity
 * @author jeo_cb
 * @date 2019/11/5
 **/
public class OftenUserEntity extends BaseEntity {

    private static final long serialVersionUID = -6559604090008757830L;
    private Long id;
    private Long userId;
    private Long oftenUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOftenUserId() {
        return oftenUserId;
    }

    public void setOftenUserId(Long oftenUserId) {
        this.oftenUserId = oftenUserId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
