package com.btsoft.jf.cloud.platform.security.vo.employee;

import com.btsoft.jf.cloud.core.base.vo.impl.BaseVO;
import com.btsoft.jf.cloud.core.constant.DateFormatConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 员工VO
 * @author jeo_cb
 * @date 2019/10/5
 **/
public class EmployeeVO extends BaseVO {
    private static final long serialVersionUID = -2676036672213134227L;
    private Long userId;
    private Long employeeNo;
    private Integer employeeType;
    private Integer employeeStatus;
    private Long employeeJob;
    private String employeeJobName;
    private Long parentId;
    @JsonFormat(pattern = DateFormatConstants.DATE)
    private Date entryDate;


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

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
