package com.btsoft.jf.cloud.core.base.vo.impl;

import com.btsoft.jf.cloud.core.base.vo.IBaseAppVO;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 应用基类VO
 * @author jeo_cb
 * @date 2019/9/5
 **/
public class BaseAppVO extends BaseVO implements IBaseAppVO {

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
