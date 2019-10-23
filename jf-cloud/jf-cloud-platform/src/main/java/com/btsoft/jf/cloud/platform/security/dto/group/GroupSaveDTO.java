package com.btsoft.jf.cloud.platform.security.dto.group;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseAppDTO;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 群组保存DTO
 * @author jeo_cb
 * @date 2019/9/1
 **/
public class GroupSaveDTO extends BaseAppDTO {

    @ApiModelProperty("群组id,更新时非空")
    private Long groupId;

    @NotBlank
    @ApiModelProperty("群组编码，同应用下不能重复")
    private String groupCode;

    @NotBlank
    @ApiModelProperty("群组名称")
    private String groupName;

    @NotNull
    @ApiModelProperty("群组责任人id")
    private Long groupOwner;

    @ApiModelProperty("群组描述")
    private String groupDesc;

    @NotBlank
    @ApiModelProperty("是否可申请，Y|N")
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

    public Long getGroupOwner() {
        return groupOwner;
    }

    public void setGroupOwner(Long groupOwner) {
        this.groupOwner = groupOwner;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
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
