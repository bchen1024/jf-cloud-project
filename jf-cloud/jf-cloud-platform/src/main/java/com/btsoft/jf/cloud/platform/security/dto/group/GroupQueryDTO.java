package com.btsoft.jf.cloud.platform.security.dto.group;

import com.btsoft.jf.cloud.core.base.dto.impl.SearchKeywordAppDTO;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 群组分页查询DTO
 * @author jeo_cb
 * @date 2019/9/5
 **/
public class GroupQueryDTO extends SearchKeywordAppDTO {

    @ApiModelProperty("群组责任人")
    private Long groupOwner;
    @ApiModelProperty("是否可申请")
    private String applyStatus;

    public Long getGroupOwner() {
        return groupOwner;
    }

    public void setGroupOwner(Long groupOwner) {
        this.groupOwner = groupOwner;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
