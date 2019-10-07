package com.btsoft.jf.cloud.platform.security.vo.app;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 应用基本信息
 * @author jeo_cb
 * @date 2019/10/4
 **/
public class AppBaseVO {

    private Long appId;
    private String appCode;
    private String appName;
    private String contextPath;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
