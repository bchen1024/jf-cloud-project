package com.btsoft.jf.cloud.platform.security.dto.app;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 应用Token保存DTO
 * @author jeo_cb
 * @date 2019/10/5
 **/
public class AppTokenSaveDTO extends BaseDTO {

    private Long appId;
    private String appToken;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
