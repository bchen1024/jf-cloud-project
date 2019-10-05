package com.btsoft.jf.cloud.platform.security.service;

import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.platform.security.dto.employee.EmployeeQueryDTO;
import com.btsoft.jf.cloud.platform.security.vo.employee.EmployeeVO;

/**
 * 员工Service接口
 * @author jeo_cb
 * @date 2019/10/5
 **/
public interface IEmployeeService {
    
    /**
     * 员工分页查询
     * @author jeo_cb
     * @date 2019/10/5
     * @param  dto 查询参数
     * @return 员工信息
     **/
    CommonResult<PageResult<EmployeeVO>> findEmployeePage(EmployeeQueryDTO dto);
}
