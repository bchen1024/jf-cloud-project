package com.btsoft.jf.core.base.result.impl;

import com.btsoft.jf.core.base.page.impl.PageVO;
import com.btsoft.jf.core.base.result.IPageResult;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 分页结果
 * @author jeo_cb
 * @date 2020/1/13
 **/
public class PageResult<T> implements IPageResult<T> {

    private static final long serialVersionUID = -6889018361713380252L;
    private List<T> list;
    private PageVO page;

    @Override
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public PageVO getPage() {
        return page;
    }

    public void setPage(PageVO page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
