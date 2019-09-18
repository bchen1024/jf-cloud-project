package com.btsoft.jf.cloud.core.base.vo.impl;

import com.btsoft.jf.cloud.core.base.vo.ICascadeSelectItem;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 级联选择项VO
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class CascadeSelectItemVO extends SelectItemVO implements ICascadeSelectItem {

    private List<SelectItemVO> children;
    private Object parent;

    @Override
    public List<SelectItemVO> getChildren() {
        return children;
    }

    public void setChildren(List<SelectItemVO> children) {
        this.children = children;
    }

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
