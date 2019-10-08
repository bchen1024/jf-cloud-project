package com.btsoft.jf.cloud.platform.security.dto.auth;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 手机动态码登录
 * @author jeo_cb
 * @date 2019/10/8 19:01
 **/
public class MobileLoginDTO implements Serializable {

    private String mobile;
    private String dynamicCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDynamicCode() {
        return dynamicCode;
    }

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
