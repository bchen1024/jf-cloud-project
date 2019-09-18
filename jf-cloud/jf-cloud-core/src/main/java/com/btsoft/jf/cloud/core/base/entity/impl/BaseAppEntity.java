package com.btsoft.jf.cloud.core.base.entity.impl;

import com.btsoft.jf.cloud.core.base.entity.IBaseAppEntity;

/**
 * 应用实体基类
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class BaseAppEntity extends BaseEntity implements IBaseAppEntity {

    private String appCode;

    @Override
    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
