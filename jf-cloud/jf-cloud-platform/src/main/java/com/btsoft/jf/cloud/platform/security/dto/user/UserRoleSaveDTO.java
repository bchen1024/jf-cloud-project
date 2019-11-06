package com.btsoft.jf.cloud.platform.security.dto.user;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseAppDTO;
import com.btsoft.jf.cloud.core.constant.DateFormatConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 群组用户添加
 * @author jeo_cb
 * @date 2019/11/4 16:44
 **/
public class UserRoleSaveDTO extends BaseAppDTO {

    private static final long serialVersionUID = 3939043498328192887L;
    @NotNull
    private Long userId;
    @NotEmpty
    private List<Long> roleIds;
    @NotEmpty
    @JsonFormat(pattern = DateFormatConstants.DATE)
    private List<Date> dateRange;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
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