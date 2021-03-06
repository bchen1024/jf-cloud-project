package com.btsoft.jf.cloud.platform.account.service;

import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.account.dto.employee.EmployeeQueryDTO;
import com.btsoft.jf.cloud.platform.account.dto.employee.EmployeeUpdateDTO;
import com.btsoft.jf.cloud.platform.account.vo.employee.EmployeeVO;

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

    /**
     * 员工详情
     * @author jeo_cb
     * @date 2019/10/11
     * @param  id 员工id
     * @return 员工信息
     **/
    CommonResult<EmployeeVO> findEmployee(Long id);

    /**
     * 员工信息更新
     * @author jeo_cb
     * @date 2019/10/11
     * @param  dto 更新参数
     * @return 更新结果
     **/
    Result updateEmployee(EmployeeUpdateDTO dto);
}
