package com.btsoft.jf.cloud.core.base.result.impl;

import com.btsoft.jf.cloud.core.base.result.IPageResult;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 分页结果
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class PageResult<T> implements IPageResult<T> {

    private Integer pageSize;
    private Integer curPage;
    private Long total;
    private Integer totalPage;
    private List<T> list;

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
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
