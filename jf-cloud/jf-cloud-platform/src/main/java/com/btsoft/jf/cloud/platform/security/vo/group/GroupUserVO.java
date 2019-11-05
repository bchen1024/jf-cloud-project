package com.btsoft.jf.cloud.platform.security.vo.group;

import com.btsoft.jf.cloud.core.base.vo.impl.BaseVO;
import com.btsoft.jf.cloud.core.constant.DateFormatConstants;
import com.btsoft.jf.cloud.platform.security.vo.IGroupVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 用户角色
 * @author jeo_cb
 * @date 2019/10/30
 **/
public class GroupUserVO extends BaseVO implements IGroupVO {

    private static final long serialVersionUID = 8736124919414377276L;
    private Long id;
    private Long groupId;
    private String groupCode;
    private String groupName;
    private String groupDesc;
    private Long userId;
    @JsonFormat(pattern = DateFormatConstants.DATE)
    private Date beginDate;
    @JsonFormat(pattern = DateFormatConstants.DATE)
    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    @Override
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    @Override
    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
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
