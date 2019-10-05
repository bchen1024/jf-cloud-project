package com.btsoft.jf.cloud.platform.security.dto.app;

import com.btsoft.jf.cloud.core.base.dto.impl.SearchKeywordDTO;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 角色分页查询DTO
 * @author jeo_cb
 * @date 2019/9/5
 **/
public class AppQueryDTO extends SearchKeywordDTO {

    @ApiModelProperty("应用责任人id")
    private Long appOwner;

    @ApiModelProperty("应用类型")
    private String appType;

    public Long getAppOwner() {
        return appOwner;
    }

    public void setAppOwner(Long appOwner) {
        this.appOwner = appOwner;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
