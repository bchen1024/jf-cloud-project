package com.btsoft.jf.cloud.core.base.vo.impl;

import com.btsoft.jf.cloud.core.base.vo.ISelectItemVO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 选择项VO
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class SelectItemVO implements ISelectItemVO {

    private Object value;
    private Object label;
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
    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    @Override
    public Boolean getDisabled() {
        if(disabled==null){
            disabled=false;
        }
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public Boolean getSelected() {
        if(selected==null){
            selected=false;
        }
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
