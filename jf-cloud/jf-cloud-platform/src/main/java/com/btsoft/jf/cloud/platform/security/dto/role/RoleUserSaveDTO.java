package com.btsoft.jf.cloud.platform.security.dto.role;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseAppDTO;
import com.btsoft.jf.cloud.core.constant.DateFormatConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 群组角色保存DTO
 * @author jeo_cb
 * @date 2019/10/30
 **/
public class RoleUserSaveDTO extends BaseAppDTO {

    private static final long serialVersionUID = 3166592272072758000L;
    @NotNull
    private Long roleId;
    @NotEmpty
    private List<Long> userIds;
    @NotEmpty
    @JsonFormat(pattern = DateFormatConstants.DATE)
    private List<Date> dateRange;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public List<Date> getDateRange() {
        return dateRange;
    }

    public void setDateRange(List<Date> dateRange) {
        this.dateRange = dateRange;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
