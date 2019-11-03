package com.btsoft.jf.cloud.platform.security.entity;

import com.btsoft.jf.cloud.core.base.entity.impl.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 员工实体
 * @author jeo_cb
 * @date 2019/10/5
 **/
public class EmployeeEntity extends BaseEntity {

    private static final long serialVersionUID = -8701232603813806397L;
    private Long userId;
    private Long employeeNo;
    private Integer employeeType;
    private Integer employeeStatus;
    private Long employeeJob;
    private Long parentId;
    private Date entryDate;
    private Long employeeOrg;

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
