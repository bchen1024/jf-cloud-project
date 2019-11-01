package com.btsoft.jf.cloud.core.base.dto.impl;

import com.btsoft.jf.cloud.core.base.dto.IBaseAppDTO;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 应用分页DTO
 * @author jeo_cb
 * @date 2019/9/5
 **/
public class PageAppDTO extends PageDTO implements IBaseAppDTO {

    private static final long serialVersionUID = -516979503693375594L;
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
