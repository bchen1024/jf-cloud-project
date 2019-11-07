package com.btsoft.jf.cloud.platform.auth.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 登录结果VO
 * @author jeo_cb
 * @date 2019/9/3
 **/
public class LoginVO implements Serializable {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
