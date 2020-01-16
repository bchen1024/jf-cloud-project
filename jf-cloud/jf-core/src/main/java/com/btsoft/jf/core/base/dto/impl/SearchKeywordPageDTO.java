package com.btsoft.jf.core.base.dto.impl;

import com.btsoft.jf.core.base.dto.ISearchKeywordPageDTO;
import com.btsoft.jf.core.base.page.impl.PageDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 关键字分页查询DTO
 * @author jeo_cb
 * @date 2020/1/13
 **/
public class SearchKeywordPageDTO extends PageDTO implements ISearchKeywordPageDTO {
    private static final long serialVersionUID = -7835583128710100983L;
    private String keywordField;
    private Object keywordValue;

    @Override
    public String getKeywordField() {
        return keywordField;
    }

    public void setKeywordField(String keywordField) {
        this.keywordField = keywordField;
    }

    @Override
    public Object getKeywordValue() {
        return keywordValue;
    }

    public void setKeywordValue(Object keywordValue) {
        this.keywordValue = keywordValue;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
