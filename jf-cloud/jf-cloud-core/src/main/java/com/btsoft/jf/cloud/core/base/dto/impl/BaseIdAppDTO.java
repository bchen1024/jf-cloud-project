package com.btsoft.jf.cloud.core.base.dto.impl;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 基类id DTO
 * @author jeo_cb
 * @date 2019/9/5
 **/
public class BaseIdAppDTO extends BaseAppDTO {

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
