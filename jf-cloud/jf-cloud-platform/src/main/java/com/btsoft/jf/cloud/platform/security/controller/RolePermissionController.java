package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.platform.security.dto.permission.PermissionQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.role.RolePermissionSaveDTO;
import com.btsoft.jf.cloud.platform.security.service.IPermissionService;
import com.btsoft.jf.cloud.platform.security.service.IRoleService;
import com.btsoft.jf.cloud.platform.security.vo.permission.PermissionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色权限Controller
 * @author jeo_cb
 * @date 2019/10/11
 **/
@RestController
@Api(tags = "角色权限管理")
@RequestMapping("/security/role/permission")
@JResource(code = "rolePermission", descCN = "角色权限", descEN = "Role Permission",parentCode = "role",sort = 1)
public class RolePermissionController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    /**
     * @author jeo_cb
     * @description 获取角色权限树
     * @date 2019/10/10
     * @param dto 权限树查询参数
     * @return 权限树
     */
    @ApiOperation("角色权限树")
    @PostMapping(ControllerConstants.Path.TREE)
    @JOperator(code = ControllerConstants.Operator.TREE, descCN = "角色权限树", descEN = "Role Permission Tree",sort = 10)
    public CommonResult<List<PermissionVO>> findPermissionTree(@RequestBody PermissionQueryDTO dto){
        return permissionService.findPermissionTree(dto);
    }

    /**
     * @author jeo_cb
     * @description 保存角色权限
     * @date 2019/10/10
     * @param dto 角色权限关系数据
     * @return 保存结果
     */
    @ApiOperation("保存角色权限")
    @PutMapping(ControllerConstants.Path.SAVE)
    @JOperator(code = ControllerConstants.Operator.SAVE, descCN = "保存角色权限", descEN = "Save Role Permission",sort = 20)
    public Result saveRolePermission(@RequestBody RolePermissionSaveDTO dto){
        return roleService.saveRolePermission(dto);
    }
}
