package com.btsoft.jf.cloud.core.base.dto.impl;

import com.btsoft.jf.cloud.core.base.dto.IBaseDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 基类DTO
 * @author jeo_cb
 * @date 2019/9/1
 **/
public class BaseDTO implements IBaseDTO {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
