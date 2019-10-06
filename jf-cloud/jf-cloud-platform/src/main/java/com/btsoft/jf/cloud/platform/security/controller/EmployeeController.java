package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.constant.ControllerContants;
import com.btsoft.jf.cloud.platform.security.dto.employee.EmployeeQueryDTO;
import com.btsoft.jf.cloud.platform.security.service.IEmployeeService;
import com.btsoft.jf.cloud.platform.security.vo.employee.EmployeeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 员工Controller
 * @author jeo_cb
 * @date 2019/10/5
 **/
@RestController
@RequestMapping("/security/employee")
@Api(tags = "员工管理")
@JResource(code = "employee", descCN = "员工管理", descEN = "Employee")
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
    @PostMapping(ControllerContants.Path.PAGE)
    @ApiOperation("分页查询员工")
    @JOperator(code = ControllerContants.Operator.PAGE, descCN = "员工列表", descEN = "Employee List")
    public CommonResult<PageResult<EmployeeVO>> findAppPage(@RequestBody EmployeeQueryDTO dto){
        return service.findEmployeePage(dto);
    }
}
