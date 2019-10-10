package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerContants;
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
@RequestMapping("/security/app/user")
@Api(tags = "应用用户管理")
@JResource(code = "appUsers", descCN = "应用用户管理", descEN = "App User",parentCode = "app")
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
    @PostMapping(ControllerContants.Path.PAGE)
    @ApiOperation("分页查询应用用户")
    @JOperator(code =ControllerContants.Operator.PAGE, descCN = "应用用户列表", descEN = "App User List")
    public CommonResult<PageResult<AppUserVO>> findAppUserPage(@RequestBody AppUserQueryDTO dto){
        return service.findAppUserPage(dto);
    }

    /**
     * 删除单个应用用户
     * @author jeo_cb
     * @date 2019/9/29
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @DeleteMapping(ControllerContants.Path.DELETE)
    @ApiOperation("删除应用用户")
    @JOperator(code =ControllerContants.Operator.DELETE, descCN = "删除应用用户", descEN = "Delete App User")
    @JAuditLog
    public Result deleteAppUser(@RequestBody BaseIdDTO dto){
        return service.deleteAppUser(dto);
    }

    /**
     * 添加应用用户
     * @author jeo_cb
     * @date 2019/10/4
     * @param  dto 保存参数
     * @return 保存结果
     **/
    @PostMapping(ControllerContants.Path.CREATE)
    @ApiOperation("添加应用用户")
    @JOperator(code =ControllerContants.Operator.CREATE, descCN = "添加应用用户", descEN = "Add App User")
    @JAuditLog
    public Result addAppUser(@RequestBody AppUserSaveDTO dto){
        return service.addAppUser(dto);
    }
}
