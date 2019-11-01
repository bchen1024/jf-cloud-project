package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupUserSaveDTO;
import com.btsoft.jf.cloud.platform.security.service.IGroupService;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 群组用户Contollerr
 * @author jeo_cb
 * @date 2019/10/30
 **/
@RestController
@Api(tags = "群组用户管理")
@RequestMapping("/security/group/user")
@JResource(code="groupUsers",descCN = "群组用户",descEN = "Group User",sort = 1,parentCode = "group")
public class GroupUserController {

    @Autowired
    private IGroupService service;

    /**
     * 查询群组用户列表
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 查询参数
     * @return 群组用户列表
     **/
    @ApiOperation("分页查询群组用户")
    @PostMapping(ControllerConstants.Path.PAGE)
    @JOperator(code = ControllerConstants.Operator.PAGE, descCN = "用户列表", descEN = "User List",sort = 10)
    public CommonResult<PageResult<GroupUserVO>> findGroupUserList(@RequestBody GroupUserQueryDTO dto){
        return service.findGroupUserList(dto);
    }

    /**
     * 添加群组用户
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 保存参数
     * @return 保存结果
     **/
    @JAuditLog
    @ApiOperation("添加用户")
    @PostMapping(ControllerConstants.Path.CREATE)
    @JOperator(code = ControllerConstants.Operator.CREATE, descCN = "添加用户", descEN = "Add User",sort = 20)
    public Result addGroupUser(@Valid @RequestBody GroupUserSaveDTO dto){
        return service.addGroupUser(dto);
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
    public Result deleteGroupUser(@RequestBody BaseIdDTO dto){
        return service.deleteGroupUser(dto);
    }
}
