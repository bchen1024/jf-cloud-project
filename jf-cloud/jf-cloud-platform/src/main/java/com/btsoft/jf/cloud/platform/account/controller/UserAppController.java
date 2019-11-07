package com.btsoft.jf.cloud.platform.account.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.platform.security.dto.app.AppUserQueryDTO;
import com.btsoft.jf.cloud.platform.account.dto.user.UserAppSaveDTO;
import com.btsoft.jf.cloud.platform.security.service.IAppUserService;
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
@Api(tags = "用户应用管理")
@RequestMapping("/security/user/app")
@JResource(code = "userApps", descCN = "用户应用", descEN = "User App",parentCode = "user",sort = 1)
public class UserAppController {

    @Autowired
    private IAppUserService service;

    /**
     * 用户应用列表
     * @author jeo_cb
     * @date 2019/10/3
     * @param  dto 查询参数
     * @return 用户应用
     **/
    @ApiOperation("分页查询用户应用")
    @PostMapping(ControllerConstants.Path.PAGE)
    @JOperator(code = ControllerConstants.Operator.PAGE, descCN = "用户应用列表", descEN = "User App List",sort = 10)
    public CommonResult<PageResult<AppUserVO>> findUserAppPage(@RequestBody AppUserQueryDTO dto){
        return service.findAppUserPage(dto);
    }

    /**
     * 添加应用
     * @author jeo_cb
     * @date 2019/10/4
     * @param  dto 保存参数
     * @return 保存结果
     **/
    @JAuditLog
    @ApiOperation("添加应用")
    @PostMapping(ControllerConstants.Path.CREATE)
    @JOperator(code = ControllerConstants.Operator.CREATE, descCN = "添加应用", descEN = "Add App",sort = 20)
    public Result addAppUser(@RequestBody UserAppSaveDTO dto){
        return service.addUserApp(dto);
    }

    /**
     * 删除应用
     * @author jeo_cb
     * @date 2019/9/29
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @JAuditLog
    @ApiOperation("删除用户")
    @DeleteMapping(ControllerConstants.Path.DELETE)
    @JOperator(code = ControllerConstants.Operator.DELETE, descCN = "删除应用", descEN = "Delete App",sort = 30)
    public Result deleteAppUser(@RequestBody BaseIdDTO dto){
        return service.deleteAppUser(dto);
    }

}
