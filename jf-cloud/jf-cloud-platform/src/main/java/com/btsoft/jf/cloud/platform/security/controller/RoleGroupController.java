package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupRoleQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleGroupSaveDTO;
import com.btsoft.jf.cloud.platform.security.service.IGroupRoleService;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupRoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 角色群组Controller
 * @author jeo_cb
 * @date 2019/10/30
 **/
@RestController
@Api(tags = "角色群组管理")
@RequestMapping("/security/role/group")
@JResource(code="roleGroups",descCN = "角色群组",descEN = "Role Group",sort = 2,parentCode = "role")
public class RoleGroupController {

    @Autowired
    private IGroupRoleService service;

    /**
     * 查询角色群组列表
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 查询参数
     * @return 角色群组列表
     **/
    @ApiOperation("分页查询角色群组")
    @PostMapping(ControllerConstants.Path.PAGE)
    @JOperator(code = ControllerConstants.Operator.PAGE, descCN = "群组列表", descEN = "Group List",sort = 10)
    public CommonResult<PageResult<GroupRoleVO>> findRoleGroupList(@RequestBody GroupRoleQueryDTO dto){
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
    @ApiOperation("添加群组")
    @PostMapping(ControllerConstants.Path.CREATE)
    @JOperator(code = ControllerConstants.Operator.CREATE, descCN = "添加群组", descEN = "Add Group",sort = 20)
    public Result addGroupRole(@Valid @RequestBody RoleGroupSaveDTO dto){
        return service.addRoleGroup(dto);
    }

    /**
     * 删除群组
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @JAuditLog
    @ApiOperation("删除群组")
    @DeleteMapping(ControllerConstants.Path.DELETE)
    @JOperator(code = ControllerConstants.Operator.DELETE, descCN = "删除群组", descEN = "Delete Group",sort = 30)
    public Result deleteGroupRole(@RequestBody BaseIdDTO dto){
        return service.deleteGroupRole(dto);
    }
}
