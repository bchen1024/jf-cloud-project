package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerContants;
import com.btsoft.jf.cloud.platform.security.dto.permission.PermissionSyncDTO;
import com.btsoft.jf.cloud.platform.security.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 类描述
 *
 * @author jeo_cb
 * @date 2019/9/3
 **/
@RestController
@RequestMapping("/security/permission")
@Api(tags = "权限管理")
@JResource(code = "permission", descCN = "权限管理", descEN = "Permission")
public class PermissionController {

    @Autowired
    private IPermissionService service;

    /**
     * 权限同步
     * @author jeo_cb
     * @date 2019/10/6
     * @param  dto 同步参数
     * @return 同步结果
     **/
    @PostMapping(ControllerContants.Path.SYNC)
    @ApiOperation("同步权限")
    @JOperator(code = ControllerContants.Operator.SYNC, descCN = "同步权限", descEN = "Sync Permission")
    @JAuditLog
    public Result syncPermission(@RequestBody PermissionSyncDTO dto){
        return service.syncPermission(dto);
    }
}
