package com.btsoft.jf.cloud.platform.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 更新密码DTO
 * @author jeo_cb
 * @date 2019/10/28 16:41
 **/
public class UpdatePasswordDTO implements Serializable {
    private static final long serialVersionUID = 6052211303555331056L;

    @NotBlank
    @ApiModelProperty("原密码")
    private String originalPassword;

    @NotBlank
    @ApiModelProperty("新密码")
    private String newPassword;

    @NotBlank
    @ApiModelProperty("确认密码")
    private String confirmPassword;

    @NotBlank
    @ApiModelProperty("验证码")
    private String validateCode;

    public String getOriginalPassword() {
        return originalPassword;
    }

    public void setOriginalPassword(String originalPassword) {
        this.originalPassword = originalPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
