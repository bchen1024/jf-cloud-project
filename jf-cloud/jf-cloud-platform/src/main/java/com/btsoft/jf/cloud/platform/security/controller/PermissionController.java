package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.platform.security.dto.permission.PermissionQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.permission.PermissionSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.permission.PermissionSyncDTO;
import com.btsoft.jf.cloud.platform.security.service.IPermissionService;
import com.btsoft.jf.cloud.platform.security.vo.permission.PermissionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限管理Controller
 * @author jeo_cb
 * @date 2019/9/3
 **/
@RestController
@Api(tags = "权限管理")
@RequestMapping("/security/permission")
@JResource(code = "permission", descCN = "权限管理", descEN = "Permission",sort = 60)
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
    @JAuditLog
    @ApiOperation("同步权限")
    @PostMapping(ControllerConstants.Path.SYNC)
    @JOperator(code = ControllerConstants.Operator.SYNC, descCN = "同步权限", descEN = "Sync Permission",sort = 10)
    public Result syncPermission(@RequestBody PermissionSyncDTO dto){
        return service.syncPermission(dto);
    }

    /**
     * 获取权限树
     * @author jeo_cb
     * @date 2019/10/7
     * @param  dto 查询参数
     * @return 权限树集合
     **/
    @ApiOperation("获取权限树")
    @PostMapping(ControllerConstants.Path.TREE)
    @JOperator(code = ControllerConstants.Operator.TREE, descCN = "权限点树", descEN = "Permission Tree",sort = 20)
    public CommonResult<List<PermissionVO>> findPermissionTree(@RequestBody PermissionQueryDTO dto){
        return service.findPermissionTree(dto);
    }

    /**
     * 保存权限点
     * @author jeo_cb
     * @date 2019/10/7
     * @param  dto 查询参数
     * @return 保存结果
     **/
    @JAuditLog
    @ApiOperation("保存权限")
    @PutMapping(ControllerConstants.Path.SAVE)
    @JOperator(code = ControllerConstants.Operator.SAVE, descCN = "保存权限点", descEN = "Save Permission",sort = 30)
    public Result savePermission(@RequestBody PermissionSaveDTO dto){
        return service.savePermission(dto);
    }

    /**
     * 删除单个权限点
     * @author jeo_cb
     * @date 2019/10/11
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @JAuditLog
    @ApiOperation("删除权限点")
    @DeleteMapping(ControllerConstants.Path.DELETE)
    @JOperator(code = ControllerConstants.Operator.DELETE, descCN = "删除权限点", descEN = "Delete Permission",sort = 40)
    public Result deletePermission(@RequestBody BaseIdDTO dto){
        return service.deletePermission(dto);
    }
}
