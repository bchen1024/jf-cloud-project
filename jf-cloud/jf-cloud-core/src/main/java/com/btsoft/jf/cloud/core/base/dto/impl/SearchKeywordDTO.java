package com.btsoft.jf.cloud.core.base.dto.impl;

import com.btsoft.jf.cloud.core.base.dto.ISearchKeyword;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 搜索关键字
 * @author jeo_cb
 * @date 2019/9/7
 **/
public class SearchKeywordDTO extends BaseDTO implements ISearchKeyword {

    @ApiModelProperty("关键字字段")
    private String keywordField;
    @ApiModelProperty("关键字值")
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
