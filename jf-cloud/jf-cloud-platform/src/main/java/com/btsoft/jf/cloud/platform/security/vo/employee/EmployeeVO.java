package com.btsoft.jf.cloud.platform.security.vo.employee;

import com.btsoft.jf.cloud.core.base.vo.impl.BaseVO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 员工VO
 * @author jeo_cb
 * @date 2019/10/5
 **/
public class EmployeeVO extends BaseVO {
    private Long userId;
    private Long employeeNo;
    private String employeeType;
    private String employeeStatus;
    private Long employeeJob;
    private String employeeJobName;
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

    public String getEmployeeJobName() {
        return employeeJobName;
    }

    public void setEmployeeJobName(String employeeJobName) {
        this.employeeJobName = employeeJobName;
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
