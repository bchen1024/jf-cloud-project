package com.btsoft.jf.cloud.platform.security.vo.group;

import com.btsoft.jf.cloud.core.base.vo.impl.BaseAppVO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 群组VO
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class GroupVO extends BaseAppVO {

    private Long groupId;
    private String groupCode;
    private String groupName;
    private String groupDesc;
    private Long groupOwner;
    private String applyStatus;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

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
