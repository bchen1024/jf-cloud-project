package com.btsoft.jf.cloud.platform.security.dto.group;

import com.btsoft.jf.cloud.core.base.dto.impl.SearchKeywordDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 群组用户查询DTO
 * @author jeo_cb
 * @date 2019/10/30
 **/
public class GroupUserQueryDTO extends SearchKeywordDTO {

    private Long groupId;
    private Long userId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
