package com.btsoft.jf.cloud.platform.security.entity;


import com.btsoft.jf.cloud.core.base.entity.impl.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 应用实体类
 * @author jeo_cb
 * @date 2019/9/2
 **/
public class AppEntity extends BaseEntity {

    private Long appId;
    private String appCode;
    private String appName;
    private String contextPath;
    private String appDesc;
    private Long appOwner;
    private String appType;
    private String appToken;
    private Integer appPort;

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

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public Integer getAppPort() {
        return appPort;
    }

    public void setAppPort(Integer appPort) {
        this.appPort = appPort;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
