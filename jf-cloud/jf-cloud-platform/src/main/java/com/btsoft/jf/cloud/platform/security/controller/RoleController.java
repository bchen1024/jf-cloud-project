package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleSaveDTO;
import com.btsoft.jf.cloud.platform.security.service.IRoleService;
import com.btsoft.jf.cloud.platform.security.vo.role.RoleBaseVO;
import com.btsoft.jf.cloud.platform.security.vo.role.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Role管理Controller
 * @author jeo_cb
 * @date 2019/8/28
 **/
@RestController
@RequestMapping("/security/role")
@Api(tags = "角色管理")
@JResource(code = "role", descCN = "角色管理", descEN = "Role",sort = 40)
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 分页查询角色列表
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 查询参数
     * @return 角色分页列表
     **/
    @PostMapping(ControllerConstants.Path.PAGE)
    @ApiOperation("分页查询角色")
    @JOperator(code = ControllerConstants.Operator.PAGE, descCN = "角色列表", descEN = "Role List",sort = 10)
    public CommonResult<PageResult<RoleVO>> findRolePage(@RequestBody RoleQueryDTO dto){
        return roleService.findRolePage(dto);
    }

    /**
     * 根据角色id获取单个角色
     * @author jeo_cb
     * @date 2019/9/5
     * @param  id 角色
     * @return 角色信息
     **/
    @ApiOperation("角色详情")
    @GetMapping(ControllerConstants.Path.SINGLE)
    @JOperator(code = ControllerConstants.Operator.SINGLE, descCN = "角色详情", descEN = "Role Detail",sort = 20)
    public CommonResult<RoleVO> findRole(Long id){
        return roleService.findRole(id);
    }

    /**
     * 角色保存，存在角色id则修改，不存在则新增
     * @author jeo_cb
     * @date 2019/9/1
     * @param  dto 角色保存参数
     * @return 保存结果
     **/
    @JAuditLog
    @ApiOperation("保存角色，创建或者更新")
    @PutMapping(ControllerConstants.Path.SAVE)
    @JOperator(code = ControllerConstants.Operator.SAVE, descCN = "保存角色", descEN = "Save Role",sort = 30)
    public Result saveRole(@RequestBody RoleSaveDTO dto){
        return roleService.saveRole(dto);
    }

    /**
     * 删除单个角色
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @JAuditLog
    @ApiOperation("删除角色")
    @DeleteMapping(ControllerConstants.Path.DELETE)
    @JOperator(code = ControllerConstants.Operator.DELETE, descCN = "删除角色", descEN = "Delete Role",sort = 40)
    public Result deleteRole(@RequestBody BaseIdDTO dto){
        return roleService.deleteRole(dto);
    }

    /**
     * 获取可选择的角色
     * @author jeo_cb
     * @date 2019/10/6
     * @return 角色列表
     **/
    @ApiOperation("选择角色")
    @PostMapping("/select")
    public CommonResult<List<RoleBaseVO>> findRoleList(@RequestBody RoleQueryDTO dto){
        return roleService.findRoleList(dto);
    }
}
