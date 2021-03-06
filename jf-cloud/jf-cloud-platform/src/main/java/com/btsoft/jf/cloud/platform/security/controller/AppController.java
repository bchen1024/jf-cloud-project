package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.platform.security.dto.app.AppQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppSaveDTO;
import com.btsoft.jf.cloud.platform.security.service.IAppService;
import com.btsoft.jf.cloud.platform.security.vo.app.AppBaseVO;
import com.btsoft.jf.cloud.platform.security.vo.app.AppVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 应用管理Controller
 * @author jeo_cb
 * @date 2019/9/3
 **/
@RestController
@Api(tags = "应用管理")
@RequestMapping("/security/app")
@JResource(code = "app", descCN = "应用管理", descEN = "App",sort = 10)
public class AppController {

    @Autowired
    private IAppService service;

    /**
     * 分页查询列表
     * @author jeo_cb
     * @date 2019/9/29
     * @param  dto 查询参数
     * @return 分页列表
     **/
    @ApiOperation("分页查询应用")
    @PostMapping(ControllerConstants.Path.PAGE)
    @JOperator(code = ControllerConstants.Operator.PAGE, descCN = "应用列表", descEN = "App List",sort = 10)
    public CommonResult<PageResult<AppVO>> findAppPage(@RequestBody AppQueryDTO dto){
        return service.findAppPage(dto);
    }

    /**
     * 根据id获取单个对象
     * @author jeo_cb
     * @date 2019/9/29
     * @param  id 应用id
     * @return 应用信息
     **/
    @ApiOperation("应用详情")
    @GetMapping(ControllerConstants.Path.SINGLE)
    @JOperator(code = ControllerConstants.Operator.SINGLE, descCN = "应用详情", descEN = "App Detail",sort = 20)
    public CommonResult<AppVO> findApp(@Valid  Long id){
        return service.findApp(id);
    }

    /**
     * 应用保存，存在应用id则修改，不存在则新增
     * @author jeo_cb
     * @date 2019/9/29
     * @param  dto 应用保存参数
     * @return 保存结果
     **/
    @JAuditLog
    @ApiOperation("保存应用，创建或者更新")
    @PutMapping(ControllerConstants.Path.SAVE)
    @JOperator(code = ControllerConstants.Operator.SAVE, descCN = "保存应用", descEN = "Save App",sort = 30)
    public Result saveApp(@Valid @RequestBody AppSaveDTO dto){
        return service.saveApp(dto);
    }

    /**
     * 删除单个应用
     * @author jeo_cb
     * @date 2019/9/29
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @JAuditLog
    @ApiOperation("删除应用")
    @DeleteMapping(ControllerConstants.Path.DELETE)
    @JOperator(code = ControllerConstants.Operator.DELETE, descCN = "删除应用", descEN = "Delete App",sort = 40)
    public Result deleteApp(@Valid @RequestBody BaseIdDTO dto){
        return service.deleteApp(dto);
    }

    /**
     * 获取可选择的应用
     * @author jeo_cb
     * @date 2019/11/5
     * @return 应用列表
     **/
    @ApiOperation("选择应用")
    @PostMapping("/select")
    public CommonResult<List<AppBaseVO>> findAppList(@RequestBody AppQueryDTO dto){
        return service.findAppList(dto);
    }
}
