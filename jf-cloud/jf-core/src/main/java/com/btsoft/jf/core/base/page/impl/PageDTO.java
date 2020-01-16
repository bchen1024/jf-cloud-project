package com.btsoft.jf.core.base.page.impl;

import com.btsoft.jf.core.base.page.IPage;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 分页DTO
 * @author jeo_cb
 * @date 2020/1/13
 **/
public class PageDTO implements IPage {

    private static final long serialVersionUID = 3589360647005630633L;
    private Integer pageSize;
    private Integer curPage;

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
