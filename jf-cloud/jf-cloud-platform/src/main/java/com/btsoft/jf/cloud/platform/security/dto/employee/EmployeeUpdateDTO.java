package com.btsoft.jf.cloud.platform.security.dto.employee;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author jeo_cb
 * @desc 员工信息更新DTO
 * @date 2019/10/11 16:50
 **/
public class EmployeeUpdateDTO extends BaseDTO {

    private Long userId;
    private String employeeType;
    private String employeeStatus;
    private Long employeeJob;
    private Long parentId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Long getEmployeeJob() {
        return employeeJob;
    }

    public void setEmployeeJob(Long employeeJob) {
        this.employeeJob = employeeJob;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
