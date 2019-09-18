package com.btsoft.jf.cloud.core.base.dto.impl;

import com.btsoft.jf.cloud.core.base.dto.ISort;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 分页排序DTO
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class PageSortDTO extends PageDTO implements ISort{
    private List<BaseSortDTO> sorts;
    @Override
    public List<BaseSortDTO> getSorts() {
        return sorts;
    }

    public void setSorts(List<BaseSortDTO> sorts) {
        this.sorts = sorts;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
