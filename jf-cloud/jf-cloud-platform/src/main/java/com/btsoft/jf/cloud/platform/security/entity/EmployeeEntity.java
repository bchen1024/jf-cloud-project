package com.btsoft.jf.cloud.platform.security.entity;

import com.btsoft.jf.cloud.core.base.entity.impl.BaseEntity;

/**
 * 员工实体
 * @author jeo_cb
 * @date 2019/10/5
 **/
public class EmployeeEntity extends BaseEntity {

    private Long userId;
    private Long employeeNo;
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

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
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
}
