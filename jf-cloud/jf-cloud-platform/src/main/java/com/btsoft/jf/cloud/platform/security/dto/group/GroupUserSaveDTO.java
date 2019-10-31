package com.btsoft.jf.cloud.platform.security.dto.group;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseAppDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 群组用户保存DTO
 * @author jeo_cb
 * @date 2019/10/30
 **/
public class GroupUserSaveDTO extends BaseAppDTO {

    private Long groupId;
    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "UTC+8")
    private Date beginDate;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "UTC+8")
    private Date endDate;

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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
