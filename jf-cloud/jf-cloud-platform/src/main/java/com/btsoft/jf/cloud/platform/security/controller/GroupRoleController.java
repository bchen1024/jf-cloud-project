package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerContants;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupRoleQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupRoleSaveDTO;
import com.btsoft.jf.cloud.platform.security.service.IGroupService;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupRoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 群组角色Controller
 * @author jeo_cb
 * @date 2019/10/30
 **/
@RestController
@Api(tags = "群组角色管理")
@RequestMapping("/security/group/role")
@JResource(code="groupRoles",descCN = "群组角色",descEN = "Group Role",sort = 2,parentCode = "group")
public class GroupRoleController {

    @Autowired
    private IGroupService service;

    /**
     * 查询群组角色列表
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 查询参数
     * @return 群组角色列表
     **/
    @ApiOperation("分页查询群组角色")
    @PostMapping(ControllerContants.Path.PAGE)
    @JOperator(code = ControllerContants.Operator.PAGE, descCN = "角色列表", descEN = "Role List",sort = 10)
    public CommonResult<PageResult<GroupRoleVO>> findGroupRoleList(@RequestBody GroupRoleQueryDTO dto){
        return service.findGroupRoleList(dto);
    }

    /**
     * 添加群组角色
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 保存参数
     * @return 保存结果
     **/
    @JAuditLog
    @ApiOperation("添加角色")
    @PostMapping(ControllerContants.Path.CREATE)
    @JOperator(code =ControllerContants.Operator.CREATE, descCN = "添加角色", descEN = "Add Role",sort = 20)
    public Result addGroupRole(@Valid @RequestBody GroupRoleSaveDTO dto){
        return service.addGroupRole(dto);
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
    @DeleteMapping(ControllerContants.Path.DELETE)
    @JOperator(code =ControllerContants.Operator.DELETE, descCN = "删除角色", descEN = "Delete Role",sort = 30)
    public Result deleteGroupRole(@RequestBody BaseIdDTO dto){
        return service.deleteGroupRole(dto);
    }
}
