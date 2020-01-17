package com.btsoft.jf.core.pojo.dto.impl;

import com.btsoft.jf.core.pojo.dto.ISearchKeyword;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 关键字查询DTO
 * @author jeo_cb
 * @date 2020/1/13
 **/
public class SearchKeywordDTO implements ISearchKeyword {

    private static final long serialVersionUID = -7052020640588347197L;
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
