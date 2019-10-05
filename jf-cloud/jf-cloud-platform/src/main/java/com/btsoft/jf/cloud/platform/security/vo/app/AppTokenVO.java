package com.btsoft.jf.cloud.platform.security.vo.app;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 应用token VO
 * @author jeo_cb
 * @date 2019/10/5
 **/
public class AppTokenVO {

    private String appToken;

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
