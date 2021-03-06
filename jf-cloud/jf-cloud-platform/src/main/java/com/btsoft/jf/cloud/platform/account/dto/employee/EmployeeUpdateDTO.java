package com.btsoft.jf.cloud.platform.account.dto.employee;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseDTO;
import com.btsoft.jf.cloud.core.constant.DateFormatConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * @author jeo_cb
 * @desc 员工信息更新DTO
 * @date 2019/10/11 16:50
 **/
public class EmployeeUpdateDTO extends BaseDTO {

    private static final long serialVersionUID = -3127396323173544017L;
    private Long userId;
    private Integer employeeType;
    private Integer employeeStatus;
    private Long employeePost;
    private Long parentId;
    @JsonFormat(pattern = DateFormatConstants.DATE)
    private Date entryDate;
    private Long employeeOrg;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(Integer employeeType) {
        this.employeeType = employeeType;
    }

    public Integer getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(Integer employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Long getEmployeePost() {
        return employeePost;
    }

    public void setEmployeePost(Long employeePost) {
        this.employeePost = employeePost;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Long getEmployeeOrg() {
        return employeeOrg;
    }

    public void setEmployeeOrg(Long employeeOrg) {
        this.employeeOrg = employeeOrg;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
