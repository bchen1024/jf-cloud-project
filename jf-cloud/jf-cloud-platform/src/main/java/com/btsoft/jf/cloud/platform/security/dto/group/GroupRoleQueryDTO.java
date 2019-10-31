package com.btsoft.jf.cloud.platform.security.dto.group;

import com.btsoft.jf.cloud.core.base.dto.impl.SearchKeywordDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 群组角色查询DTO
 * @author jeo_cb
 * @date 2019/10/30
 **/
public class GroupRoleQueryDTO extends SearchKeywordDTO {

    private static final long serialVersionUID = 6912428638928541405L;
    private Long groupId;
    private Long roleId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
