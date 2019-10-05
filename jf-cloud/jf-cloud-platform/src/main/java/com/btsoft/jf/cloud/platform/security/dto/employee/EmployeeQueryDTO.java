package com.btsoft.jf.cloud.platform.security.dto.employee;

import com.btsoft.jf.cloud.core.base.dto.impl.SearchKeywordDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 员工查询DTO
 * @author jeo_cb
 * @date 2019/10/5
 **/
public class EmployeeQueryDTO extends SearchKeywordDTO {
    private String employeeType;
    private String employeeStatus;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
