package com.btsoft.jf.core.base.page.impl;

import com.btsoft.jf.core.base.page.IPage;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 分页VO
 * @author jeo_cb
 * @date 2020/1/13
 **/
public class PageVO implements IPage {

    private static final long serialVersionUID = 6737936329754720011L;
    private Integer pageSize;
    private Integer curPage;
    private Long total;
    private Integer totalPage;

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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
