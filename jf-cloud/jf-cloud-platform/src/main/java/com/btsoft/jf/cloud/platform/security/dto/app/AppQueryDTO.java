package com.btsoft.jf.cloud.platform.security.dto.app;

import com.btsoft.jf.cloud.core.base.dto.impl.SearchKeywordAppDTO;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 角色分页查询DTO
 * @author jeo_cb
 * @date 2019/9/5
 **/
public class AppQueryDTO extends SearchKeywordAppDTO {

    @ApiModelProperty("应用责任人id")
    private Long appOwner;

    public Long getAppOwner() {
        return appOwner;
    }

    public void setAppOwner(Long appOwner) {
        this.appOwner = appOwner;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
