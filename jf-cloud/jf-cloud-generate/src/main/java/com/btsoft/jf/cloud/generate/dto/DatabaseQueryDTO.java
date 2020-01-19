package com.btsoft.jf.cloud.generate.dto;

import com.btsoft.jf.core.pojo.dto.impl.SearchKeywordPageDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 数据库查询DTO
 * @author jeo_cb
 * @date 2020/1/13
 **/
public class DatabaseQueryDTO extends SearchKeywordPageDTO {
    private static final long serialVersionUID = -4332954293568108804L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
