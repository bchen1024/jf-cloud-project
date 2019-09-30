package com.btsoft.jf.cloud.core.base.dto.impl;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author jeo_cb
 * @desc 基类主键id DTO
 * @date 2019/9/30 13:37
 **/
public class BaseIdDTO extends BaseDTO{

    @ApiModelProperty("主键id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
