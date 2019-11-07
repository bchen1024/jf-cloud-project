package com.btsoft.jf.cloud.platform.account.dto.user;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseDTO;
import com.btsoft.jf.cloud.core.constant.DateFormatConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 用户应用保存DTO
 * @author jeo_cb
 * @date 2019/10/4
 **/
public class UserAppSaveDTO extends BaseDTO {

    private static final long serialVersionUID = 3705004361703874301L;
    @NotNull
    private Long userId;
    @NotEmpty
    private List<Long> appIds;
    @NotNull
    private Long roleId;
    @NotEmpty
    @JsonFormat(pattern = DateFormatConstants.DATE)
    private List<Date> dateRange;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getAppIds() {
        return appIds;
    }

    public void setAppIds(List<Long> appIds) {
        this.appIds = appIds;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
