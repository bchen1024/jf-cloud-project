package com.btsoft.jf.cloud.platform.account.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleUserQueryDTO;
import com.btsoft.jf.cloud.platform.account.dto.user.UserRoleSaveDTO;
import com.btsoft.jf.cloud.platform.security.service.IRoleUserService;
import com.btsoft.jf.cloud.platform.security.vo.role.RoleUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 角色用户Controller
 * @author jeo_cb
 * @date 2019/10/30
 **/
@RestController
@Api(tags = "用户角色管理")
@RequestMapping("/security/user/role")
@JResource(code="userRoles",descCN = "用户角色",descEN = "User Role",sort = 1,parentCode = "user")
public class UserRoleController {

    @Autowired
    private IRoleUserService service;

    /**
     * 查询用户角色列表
     * @author jeo_cb
     * @date 2019/11/4
     * @param  dto 查询参数
     * @return 用户角色列表
     **/
    @ApiOperation("分页查询用户角色")
    @PostMapping(ControllerConstants.Path.PAGE)
    @JOperator(code = ControllerConstants.Operator.PAGE, descCN = "角色列表", descEN = "Role List",sort = 10)
    public CommonResult<PageResult<RoleUserVO>> findUserRoleList(@RequestBody RoleUserQueryDTO dto){
        return service.findRoleUserList(dto);
    }

    /**
     * 添加用户角色
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 保存参数
     * @return 保存结果
     **/
    @JAuditLog
    @ApiOperation("添加角色")
    @PostMapping(ControllerConstants.Path.CREATE)
    @JOperator(code = ControllerConstants.Operator.CREATE, descCN = "添加角色", descEN = "Add Role",sort = 20)
    public Result addRoleUser(@Valid @RequestBody UserRoleSaveDTO dto){
        return service.addUserRole(dto);
    }

    /**
     * 删除角色
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @JAuditLog
    @ApiOperation("删除角色")
    @DeleteMapping(ControllerConstants.Path.DELETE)
    @JOperator(code = ControllerConstants.Operator.DELETE, descCN = "删除角色", descEN = "Delete Role",sort = 30)
    public Result deleteRoleUser(@RequestBody BaseIdDTO dto){
        return service.deleteRoleUser(dto);
    }
}
