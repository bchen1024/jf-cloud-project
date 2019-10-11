package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerContants;
import com.btsoft.jf.cloud.platform.security.dto.app.AppTokenSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppUserSaveDTO;
import com.btsoft.jf.cloud.platform.security.service.IAppService;
import com.btsoft.jf.cloud.platform.security.vo.app.AppTokenVO;
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
@RequestMapping("/security/app/token")
@Api(tags = "应用Token管理")
@JResource(code = "appToken", descCN = "应用Token管理", descEN = "App Token",parentCode = "app")
public class AppTokenController {

    @Autowired
    private IAppService service;

    /**
     * 获取应用Token
     * @author jeo_cb
     * @date 2019/10/5
     * @param  id 应用id
     * @return 应用token
     **/
    @GetMapping(ControllerContants.Path.SINGLE)
    @ApiOperation("获取应用Token")
    @JOperator(code =ControllerContants.Operator.SINGLE, descCN = "获取应用Token", descEN = "Single App Token")
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
    @PostMapping(ControllerContants.Path.SAVE)
    @ApiOperation("保存应用Token")
    @JOperator(code =ControllerContants.Operator.SAVE, descCN = "保存应用Token", descEN = "Save App Token")
    @JAuditLog
    public Result saveAppToken(@RequestBody AppTokenSaveDTO dto){
        return service.saveAppToken(dto);
    }
}
