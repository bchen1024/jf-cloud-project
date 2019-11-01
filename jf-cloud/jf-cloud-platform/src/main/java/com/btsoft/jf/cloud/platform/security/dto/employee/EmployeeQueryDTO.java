package com.btsoft.jf.cloud.platform.security.dto.employee;

import com.btsoft.jf.cloud.core.base.dto.impl.SearchKeywordDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 员工查询DTO
 * @author jeo_cb
 * @date 2019/10/5
 **/
public class EmployeeQueryDTO extends SearchKeywordDTO {
    private static final long serialVersionUID = -1940683593413861277L;
    private Integer employeeType;
    private Integer employeeStatus;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
