package com.btsoft.jf.cloud.platform.account.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.platform.account.dto.employee.EmployeeQueryDTO;
import com.btsoft.jf.cloud.platform.account.dto.employee.EmployeeUpdateDTO;
import com.btsoft.jf.cloud.platform.security.service.IEmployeeService;
import com.btsoft.jf.cloud.platform.account.vo.employee.EmployeeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 员工Controller
 * @author jeo_cb
 * @date 2019/10/5
 **/
@RestController
@Api(tags = "员工管理")
@RequestMapping("/security/employee")
@JResource(code = "employee", descCN = "员工管理", descEN = "Employee",sort = 30)
public class EmployeeController {

    @Autowired
    private IEmployeeService service;

    /**
     * 分页查询员工列表
     * @author jeo_cb
     * @date 2019/10/5
     * @param  dto 查询参数
     * @return 员工列表
     **/
    @ApiOperation("分页查询员工")
    @PostMapping(ControllerConstants.Path.PAGE)
    @JOperator(code = ControllerConstants.Operator.PAGE, descCN = "员工列表", descEN = "Employee List",sort = 10)
    public CommonResult<PageResult<EmployeeVO>> findEmployeePage(@RequestBody EmployeeQueryDTO dto){
        return service.findEmployeePage(dto);
    }

    /**
     * 员工详情
     * @author jeo_cb
     * @date 2019/10/11
     * @param  id 员工id
     * @return 员工信息
     **/
    @ApiOperation("员工详情")
    @GetMapping(ControllerConstants.Path.SINGLE)
    @JOperator(code = ControllerConstants.Operator.SINGLE, descCN = "员工详情", descEN = "Employee Detail",sort = 20)
    public CommonResult<EmployeeVO> findEmployee(Long id){
        return service.findEmployee(id);
    }

    /**
     * 员工信息编辑
     * @author jeo_cb
     * @date 2019/10/11
     * @param  dto 编辑参数
     * @return 编辑结果
     **/
    @JAuditLog
    @ApiOperation("编辑员工信息")
    @PutMapping(ControllerConstants.Path.UPDATE)
    @JOperator(code = ControllerConstants.Operator.UPDATE, descCN = "编辑员工", descEN = "Edit Employee",sort = 30)
    public Result updateEmployee(@RequestBody EmployeeUpdateDTO dto){
        return service.updateEmployee(dto);
    }
}
