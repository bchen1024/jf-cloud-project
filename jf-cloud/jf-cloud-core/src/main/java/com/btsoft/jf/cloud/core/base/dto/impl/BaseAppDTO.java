package com.btsoft.jf.cloud.core.base.dto.impl;

import com.btsoft.jf.cloud.core.base.dto.IBaseAppDTO;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 基类应用DTO
 * @author jeo_cb
 * @date 2019/9/1
 **/
public class BaseAppDTO implements IBaseAppDTO {

    @ApiModelProperty("应用编码")
    private String appCode;

    @Override
    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
