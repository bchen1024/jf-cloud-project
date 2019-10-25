package com.btsoft.jf.cloud.platform.security.dto.auth;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 账号密码登录DTO
 * @author jeo_cb
 * @date 2019/10/8 18:55
 **/
public class AccountLoginDTO implements Serializable {

    @NotBlank
    @ApiModelProperty("账号")
    private String userAccount;

    @NotBlank
    @ApiModelProperty("密码")
    private String password;

    @NotBlank
    @ApiModelProperty("验证码")
    private String validateCode;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
