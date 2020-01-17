package com.btsoft.jf.core.pojo.dto.impl;

import com.btsoft.jf.core.pojo.dto.IBaseDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 基类DTO
 * @author jeo_cb
 * @date 2020/1/14
 **/
public class BaseDTO implements IBaseDTO {
    private static final long serialVersionUID = 4537048228819750536L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
