package com.btsoft.jf.cloud.core.base.vo.impl;

import com.btsoft.jf.cloud.core.base.vo.ITreeVO;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 树形结构VO
 * @author jeo_cb
 * @date 2019/10/7
 **/
public class TreeVO<T> implements ITreeVO {

    private Object value;
    private String title;
    private String label;
    private Boolean expand;
    private Boolean disabled;
    private Boolean disableCheckbox;
    private Boolean selected;
    private Boolean checked;
    private List<T> children;
    private List<Object> parentIds;

    @Override
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return title;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public Boolean getExpand() {
        if(expand==null){
            expand=false;
        }
        return expand;
    }

    public void setExpand(Boolean expand) {
        this.expand = expand;
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
    public Boolean getDisableCheckbox() {
        if(disableCheckbox==null){
            disableCheckbox=false;
        }
        return disableCheckbox;
    }

    public void setDisableCheckbox(Boolean disableCheckbox) {
        this.disableCheckbox = disableCheckbox;
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
    public Boolean getChecked() {
        if(checked==null){
            checked=false;
        }
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public List<Object> getParentIds() {
        return parentIds;
    }

    public void setParentIds(List<Object> parentIds) {
        this.parentIds = parentIds;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
