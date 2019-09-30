package com.btsoft.jf.cloud.core.base.dto.impl;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author jeo_cb
 * @desc id集合入参DTO
 * @date 2019/9/30 14:01
 **/
public class BaseIdListDTO extends BaseDTO{

    private List<Long> idList;

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
