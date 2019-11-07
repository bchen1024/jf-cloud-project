package com.btsoft.jf.cloud.platform.config.basic.dto.organization;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 组织架构查询DTO
 * @author jeo_cb
 * @date 2019/10/18
 **/
public class OrganizationQueryDTO extends BaseDTO {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
