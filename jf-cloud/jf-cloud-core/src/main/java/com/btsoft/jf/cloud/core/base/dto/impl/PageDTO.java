package com.btsoft.jf.cloud.core.base.dto.impl;

import com.btsoft.jf.cloud.core.base.dto.IBasePage;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 分页DTO
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class PageDTO implements IBasePage {

    @ApiModelProperty("每页大小")
    private Integer pageSize;
    @ApiModelProperty("当前第几页")
    private Integer curPage;

    @Override
    public Integer getPageSize() {
        if(pageSize==null || pageSize<0){
            pageSize=15;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public Integer getCurPage() {
        if(curPage==null || curPage<0){
            curPage=1;
        }
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
