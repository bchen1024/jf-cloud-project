package com.btsoft.jf.cloud.core.base.dto.impl;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 验证生成DTO
 * @author jeo_cb
 * @date 2019/10/25 11:43
 **/
public class ValidateCodeDTO implements Serializable {

    @NotBlank
    @ApiModelProperty("验证码类型")
    private String type;

    @ApiModelProperty("验证码key")
    private String key;

    @ApiModelProperty("宽度")
    private Integer width;
    @ApiModelProperty("高度")
    private Integer height;
    @ApiModelProperty("干扰线数量")
    private Integer lineNum;
    @ApiModelProperty("噪点比例")
    private Float dotRate;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public Float getDotRate() {
        return dotRate;
    }

    public void setDotRate(Float dotRate) {
        this.dotRate = dotRate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
