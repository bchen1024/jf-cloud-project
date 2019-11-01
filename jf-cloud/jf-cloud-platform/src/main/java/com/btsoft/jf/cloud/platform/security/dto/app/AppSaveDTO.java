package com.btsoft.jf.cloud.platform.security.dto.app;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 应用保存DTO
 * @author jeo_cb
 * @date 2019/9/7
 **/
public class AppSaveDTO extends BaseDTO {

    private static final long serialVersionUID = 1862600659076433630L;
    @ApiModelProperty("应用id,更新时非空")
    private Long appId;
    @ApiModelProperty("应用编码，不能重复")
    private String appCode;
    @ApiModelProperty("应用名称")
    private String appName;
    @ApiModelProperty("应用责任人id")
    private Long appOwner;
    @ApiModelProperty("应用描述")
    private String appDesc;
    @ApiModelProperty("应用上下文")
    private String contextPath;
    @ApiModelProperty("应用类型")
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

    public Long getAppOwner() {
        return appOwner;
    }

    public void setAppOwner(Long appOwner) {
        this.appOwner = appOwner;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
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
