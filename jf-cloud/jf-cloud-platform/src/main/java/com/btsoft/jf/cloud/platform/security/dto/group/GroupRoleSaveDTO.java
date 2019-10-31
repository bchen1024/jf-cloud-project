package com.btsoft.jf.cloud.platform.security.dto.group;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 群组角色保存DTO
 * @author jeo_cb
 * @date 2019/10/30
 **/
public class GroupRoleSaveDTO extends BaseDTO {

    @NotNull
    private Long groupId;
    @NotEmpty
    private List<Long> roleIds;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
