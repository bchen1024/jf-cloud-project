package com.btsoft.jf.cloud.platform.config.basic.dto.post;

import com.btsoft.jf.cloud.core.base.dto.impl.SearchKeywordDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 职位查询DTO
 * @author jeo_cb
 * @date 2019/11/8
 **/
public class PostQueryDTO extends SearchKeywordDTO {

    private Long orgId;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
