package com.btsoft.jf.cloud.core.base.vo.impl;

import com.btsoft.jf.cloud.core.base.vo.ISelectItem;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 选择项VO
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class SelectItemVO implements ISelectItem {

    private Object value;
    private Object text;
    private Boolean disabled;
    private Boolean selected;

    @Override
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public Object getText() {
        return text;
    }

    public void setText(Object text) {
        this.text = text;
    }

    @Override
    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
