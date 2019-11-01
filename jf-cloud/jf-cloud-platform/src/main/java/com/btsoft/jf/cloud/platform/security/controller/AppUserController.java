package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.platform.security.dto.app.AppUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppUserSaveDTO;
import com.btsoft.jf.cloud.platform.security.service.IAppService;
import com.btsoft.jf.cloud.platform.security.vo.app.AppUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 应用用户
 * @author jeo_cb
 * @date 2019/10/10
 **/
@RestController
@Api(tags = "应用用户管理")
@RequestMapping("/security/app/user")
@JResource(code = "appUsers", descCN = "应用用户", descEN = "App User",parentCode = "app",sort = 1)
public class AppUserController {

    @Autowired
    private IAppService service;

    /**
     * 应用用户列表
     * @author jeo_cb
     * @date 2019/10/3
     * @param  dto 查询参数
     * @return 应用用户
     **/
    @ApiOperation("分页查询应用用户")
    @PostMapping(ControllerConstants.Path.PAGE)
    @JOperator(code = ControllerConstants.Operator.PAGE, descCN = "应用用户列表", descEN = "App User List",sort = 10)
    public CommonResult<PageResult<AppUserVO>> findAppUserPage(@RequestBody AppUserQueryDTO dto){
        return service.findAppUserPage(dto);
    }

    /**
     * 添加应用用户
     * @author jeo_cb
     * @date 2019/10/4
     * @param  dto 保存参数
     * @return 保存结果
     **/
    @JAuditLog
    @ApiOperation("添加用户")
    @PostMapping(ControllerConstants.Path.CREATE)
    @JOperator(code = ControllerConstants.Operator.CREATE, descCN = "添加用户", descEN = "Add User",sort = 20)
    public Result addAppUser(@RequestBody AppUserSaveDTO dto){
        return service.addAppUser(dto);
    }

    /**
     * 删除单个应用用户
     * @author jeo_cb
     * @date 2019/9/29
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @JAuditLog
    @ApiOperation("删除用户")
    @DeleteMapping(ControllerConstants.Path.DELETE)
    @JOperator(code = ControllerConstants.Operator.DELETE, descCN = "删除用户", descEN = "Delete User",sort = 30)
    public Result deleteAppUser(@RequestBody BaseIdDTO dto){
        return service.deleteAppUser(dto);
    }

}
