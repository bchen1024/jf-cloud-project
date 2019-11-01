package com.btsoft.jf.cloud.platform.security.vo.app;

import com.btsoft.jf.cloud.core.base.vo.impl.BaseVO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 应用VO
 * @author jeo_cb
 * @date 2019/9/7
 **/
public class AppVO extends BaseVO {

    private Long appId;
    private String appCode;
    private String appName;
    private String contextPath;
    private String appDesc;
    private Long appOwner;
    private Integer appType;

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

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public Long getAppOwner() {
        return appOwner;
    }

    public void setAppOwner(Long appOwner) {
        this.appOwner = appOwner;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
