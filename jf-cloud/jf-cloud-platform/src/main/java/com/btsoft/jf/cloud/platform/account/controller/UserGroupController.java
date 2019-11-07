package com.btsoft.jf.cloud.platform.account.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupUserQueryDTO;
import com.btsoft.jf.cloud.platform.account.dto.user.UserGroupSaveDTO;
import com.btsoft.jf.cloud.platform.security.service.IGroupUserService;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 群组用户Controller
 * @author jeo_cb
 * @date 2019/10/30
 **/
@RestController
@Api(tags = "群组用户管理")
@RequestMapping("/security/user/group")
@JResource(code="userGroups",descCN = "用户群组",descEN = "User Group",sort = 1,parentCode = "user")
public class UserGroupController {

    @Autowired
    private IGroupUserService service;

    /**
     * 查询用户群组列表
     * @author jeo_cb
     * @date 2019/11/4
     * @param  dto 查询参数
     * @return 用户群组列表
     **/
    @ApiOperation("分页查询用户群组")
    @PostMapping(ControllerConstants.Path.PAGE)
    @JOperator(code = ControllerConstants.Operator.PAGE, descCN = "群组列表", descEN = "Group List",sort = 10)
    public CommonResult<PageResult<GroupUserVO>> findUserGroupList(@RequestBody GroupUserQueryDTO dto){
        return service.findGroupUserList(dto);
    }

    /**
     * 添加用户群组
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 保存参数
     * @return 保存结果
     **/
    @JAuditLog
    @ApiOperation("添加群组")
    @PostMapping(ControllerConstants.Path.CREATE)
    @JOperator(code = ControllerConstants.Operator.CREATE, descCN = "添加群组", descEN = "Add Group",sort = 20)
    public Result addGroupUser(@Valid @RequestBody UserGroupSaveDTO dto){
        return service.addUserGroup(dto);
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
    public Result deleteGroupUser(@RequestBody BaseIdDTO dto){
        return service.deleteGroupUser(dto);
    }
}
