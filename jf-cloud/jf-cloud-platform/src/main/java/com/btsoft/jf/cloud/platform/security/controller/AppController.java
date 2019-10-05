package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdAppDTO;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerContants;
import com.btsoft.jf.cloud.platform.security.dto.app.*;
import com.btsoft.jf.cloud.platform.security.service.IAppService;
import com.btsoft.jf.cloud.platform.security.vo.app.AppRoleUserVO;
import com.btsoft.jf.cloud.platform.security.vo.app.AppTokenVO;
import com.btsoft.jf.cloud.platform.security.vo.app.AppVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 应用管理Controller
 * @author jeo_cb
 * @date 2019/9/3
 **/
@RestController
@RequestMapping("/security/app")
@Api(tags = "应用管理")
@JResource(code = "app", descCN = "应用管理", descEN = "App Mgt")
public class AppController {

    @Autowired
    private IAppService service;

    /**
     * 应用保存，存在应用id则修改，不存在则新增
     * @author jeo_cb
     * @date 2019/9/29
     * @param  dto 应用保存参数
     * @return 保存结果
     **/
    @PutMapping(ControllerContants.Path.SAVE)
    @ApiOperation("保存应用，创建或者更新")
    @JOperator(code = ControllerContants.Operator.SAVE, descCN = "保存应用", descEN = "Save App")
    @JAuditLog
    public Result saveApp(@RequestBody AppSaveDTO dto){
        return service.saveApp(dto);
    }

    /**
     * 删除单个应用
     * @author jeo_cb
     * @date 2019/9/29
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @DeleteMapping(ControllerContants.Path.DELETE)
    @ApiOperation("删除应用")
    @JOperator(code = ControllerContants.Operator.DELETE, descCN = "删除应用", descEN = "Delete App")
    @JAuditLog
    public Result deleteApp(@RequestBody BaseIdAppDTO dto){
        return service.deleteApp(dto);
    }

    /**
     * 根据id获取单个对象
     * @author jeo_cb
     * @date 2019/9/29
     * @param  id 应用id
     * @return 应用信息
     **/
    @GetMapping(ControllerContants.Path.SINGLE)
    @ApiOperation("根据应用id获取单个应用")
    @JOperator(code = ControllerContants.Operator.SINGLE, descCN = "应用详情", descEN = "App Detail")
    public CommonResult<AppVO> findApp(Long id){
        return service.findApp(id);
    }

    /**
     * 分页查询列表
     * @author jeo_cb
     * @date 2019/9/29
     * @param  dto 查询参数
     * @return 分页列表
     **/
    @PostMapping(ControllerContants.Path.PAGE)
    @ApiOperation("分页查询应用")
    @JOperator(code = ControllerContants.Operator.PAGE, descCN = "应用列表", descEN = "App List")
    public CommonResult<PageResult<AppVO>> findAppPage(@RequestBody AppQueryDTO dto){
        return service.findAppPage(dto);
    }

    /**
     * 应用用户列表
     * @author jeo_cb
     * @date 2019/10/3
     * @param  dto 查询参数
     * @return 应用用户
     **/
    @PostMapping("/user/page")
    @ApiOperation("分页查询应用用户")
    @JOperator(code ="appUsers", descCN = "应用用户列表", descEN = "App User List")
    public CommonResult<PageResult<AppRoleUserVO>> findAppUserPage(@RequestBody AppUserQueryDTO dto){
        return service.findAppUserPage(dto);
    }

    /**
     * 删除单个应用用户
     * @author jeo_cb
     * @date 2019/9/29
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @DeleteMapping("/user/delete")
    @ApiOperation("删除应用用户")
    @JOperator(code ="deleteAppUser", descCN = "删除应用用户", descEN = "Delete App User")
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
    @PostMapping("/user/add")
    @ApiOperation("添加应用用户")
    @JOperator(code ="addAppUser", descCN = "添加应用用户", descEN = "Add App User")
    @JAuditLog
    public Result addAppUser(@RequestBody AppUserSaveDTO dto){
        return service.addAppUser(dto);
    }

    /**
     * 获取应用Token
     * @author jeo_cb
     * @date 2019/10/5
     * @param  id 应用id
     * @return 应用token
     **/
    @GetMapping("/token")
    @ApiOperation("获取应用Token")
    @JOperator(code ="appToken", descCN = "应用Token", descEN = "App Token")
    public CommonResult<AppTokenVO> findAppToken(Long id){
        return service.findAppToken(id);
    }

    /**
     * 保存应用Token
     * @author jeo_cb
     * @date 2019/10/5
     * @param  dto 保存参数
     * @return 保存结果
     **/
    @PostMapping("/token/save")
    @ApiOperation("保存应用Token")
    @JOperator(code ="saveAppToken", descCN = "保存应用Token", descEN = "Save App Token")
    @JAuditLog
    public Result saveAppToken(@RequestBody AppTokenSaveDTO dto){
        return service.saveAppToken(dto);
    }
}
