package com.btsoft.jf.cloud.core.base.dto.impl;

import com.btsoft.jf.cloud.core.base.dto.IBaseSort;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 排序基类DTO
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class BaseSortDTO implements IBaseSort {

    private String field;
    private String sort;

    @Override
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
