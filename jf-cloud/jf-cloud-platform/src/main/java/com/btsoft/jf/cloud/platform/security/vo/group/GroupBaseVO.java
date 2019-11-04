package com.btsoft.jf.cloud.platform.security.vo.group;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 群组VO
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class GroupBaseVO implements Serializable {

    private static final long serialVersionUID = 3246426828476245586L;
    private Long groupId;
    private String groupCode;
    private String groupName;
    private String groupDesc;
    private Long groupOwner;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
