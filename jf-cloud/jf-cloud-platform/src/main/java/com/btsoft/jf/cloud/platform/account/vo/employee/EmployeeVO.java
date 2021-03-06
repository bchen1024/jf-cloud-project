package com.btsoft.jf.cloud.platform.account.vo.employee;

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
    private Long employeePost;
    private String employeePostName;
    private Long employeeOrg;
    private String employeeOrgName;
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

    public Long getEmployeePost() {
        return employeePost;
    }

    public void setEmployeePost(Long employeePost) {
        this.employeePost = employeePost;
    }

    public String getEmployeePostName() {
        return employeePostName;
    }

    public void setEmployeePostName(String employeePostName) {
        this.employeePostName = employeePostName;
    }

    public Long getEmployeeOrg() {
        return employeeOrg;
    }

    public void setEmployeeOrg(Long employeeOrg) {
        this.employeeOrg = employeeOrg;
    }

    public String getEmployeeOrgName() {
        return employeeOrgName;
    }

    public void setEmployeeOrgName(String employeeOrgName) {
        this.employeeOrgName = employeeOrgName;
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
