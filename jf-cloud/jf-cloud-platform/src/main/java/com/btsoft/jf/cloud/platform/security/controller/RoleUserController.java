package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleUserSaveDTO;
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
@Api(tags = "角色用户管理")
@RequestMapping("/security/role/user")
@JResource(code="roleUsers",descCN = "角色用户",descEN = "Group User",sort = 1,parentCode = "role")
public class RoleUserController {

    @Autowired
    private IRoleUserService service;

    /**
     * 查询角色用户列表
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 查询参数
     * @return 角色用户列表
     **/
    @ApiOperation("分页查询角色用户")
    @PostMapping(ControllerConstants.Path.PAGE)
    @JOperator(code = ControllerConstants.Operator.PAGE, descCN = "用户列表", descEN = "User List",sort = 10)
    public CommonResult<PageResult<RoleUserVO>> findRoleUserList(@RequestBody RoleUserQueryDTO dto){
        return service.findRoleUserList(dto);
    }

    /**
     * 添加角色用户
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 保存参数
     * @return 保存结果
     **/
    @JAuditLog
    @ApiOperation("添加用户")
    @PostMapping(ControllerConstants.Path.CREATE)
    @JOperator(code = ControllerConstants.Operator.CREATE, descCN = "添加用户", descEN = "Add User",sort = 20)
    public Result addRoleUser(@Valid @RequestBody RoleUserSaveDTO dto){
        return service.addRoleUser(dto);
    }

    /**
     * 删除用户
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @JAuditLog
    @ApiOperation("删除用户")
    @DeleteMapping(ControllerConstants.Path.DELETE)
    @JOperator(code = ControllerConstants.Operator.DELETE, descCN = "删除用户", descEN = "Delete User",sort = 30)
    public Result deleteRoleUser(@RequestBody BaseIdDTO dto){
        return service.deleteRoleUser(dto);
    }
}
