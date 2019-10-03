package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdAppDTO;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.app.AppQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.service.IAppService;
import com.btsoft.jf.cloud.platform.security.vo.app.AppRoleUserVO;
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
    @PutMapping("/save")
    @ApiOperation("保存应用，创建或者更新")
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
    @DeleteMapping("/delete")
    @ApiOperation("删除应用")
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
    @GetMapping("/single")
    @ApiOperation("根据应用id获取单个应用")
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
    @PostMapping("/page")
    @ApiOperation("分页查询应用")
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
    public Result deleteAppUser(@RequestBody BaseIdDTO dto){
        return service.deleteAppUser(dto);
    }
}
