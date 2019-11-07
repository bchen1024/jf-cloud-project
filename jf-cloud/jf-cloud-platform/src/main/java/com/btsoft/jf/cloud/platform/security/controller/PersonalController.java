package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import com.btsoft.jf.cloud.platform.security.dto.app.AppUserQueryDTO;
import com.btsoft.jf.cloud.platform.auth.dto.UpdatePasswordDTO;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.service.IAppUserService;
import com.btsoft.jf.cloud.platform.security.service.IGroupUserService;
import com.btsoft.jf.cloud.platform.security.service.IRoleUserService;
import com.btsoft.jf.cloud.platform.account.service.IUserService;
import com.btsoft.jf.cloud.platform.security.vo.app.AppUserVO;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupUserVO;
import com.btsoft.jf.cloud.platform.security.vo.role.RoleUserVO;
import com.btsoft.jf.cloud.platform.account.vo.user.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 个人中心Controller
 * @author jeo_cb
 * @date 2019/10/29 10:08
 **/
@RestController
@Api(tags = "个人中心")
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private IGroupUserService groupUserService;
    @Autowired
    private IRoleUserService roleUserService;

    /**
     * 用户基本信息
     * @author jeo_cb
     * @date 2019/10/29
     * @return 用户基本信息
     **/
    @ApiOperation("基本信息")
    @GetMapping("/baseInfo")
    public CommonResult<UserVO> findUser(){
        Long userId= JfCloud.getCurrent().getCurrentUserId();
        return userService.findUser(userId);
    }

    /**
     * 更新用户密码
     * @author jeo_cb
     * @date 2019/10/28
     * @param  dto 密码更新参数
     * @return 更新结果
     **/
    @ApiOperation("修改密码")
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody @Valid UpdatePasswordDTO dto){
        return userService.updatePassword(dto);
    }


    /**
     * 用户应用列表
     * @author jeo_cb
     * @date 2019/10/5
     * @param  dto 查询参数
     * @return 用户应用
     **/
    @ApiOperation("我的应用")
    @PostMapping("/myApps")
    public CommonResult<PageResult<AppUserVO>> findUserAppPage(@RequestBody AppUserQueryDTO dto){
        Long userId=JfCloud.getCurrent().getCurrentUserId();
        dto.setUserId(userId);
        return appUserService.findAppUserPage(dto);
    }

    /**
     * 我的群组
     * @author jeo_cb
     * @date 2019/10/5
     * @param  dto 查询参数
     * @return 用户群组
     **/
    @ApiOperation("我的群组")
    @PostMapping("/myGroups")
    public CommonResult<PageResult<GroupUserVO>> findGroupUserList(@RequestBody GroupUserQueryDTO dto){
        Long userId=JfCloud.getCurrent().getCurrentUserId();
        dto.setUserId(userId);
        return groupUserService.findGroupUserList(dto);
    }

    /**
     * 我的角色
     * @author jeo_cb
     * @date 2019/11/5
     * @param  dto 查询参数
     * @return 用户角色列表
     **/
    @ApiOperation("我的角色")
    @PostMapping("myRoles")
    public CommonResult<PageResult<RoleUserVO>> findUserRoleList(@RequestBody RoleUserQueryDTO dto){
        Long userId=JfCloud.getCurrent().getCurrentUserId();
        dto.setUserId(userId);
        return roleUserService.findRoleUserList(dto);
    }

    /**
     * 切换当前应用
     * @author jeo_cb
     * @date 2019/11/6
     * @return 切换结果
     */
    @ApiOperation("切换应用")
    @PostMapping("switchApp")
    public Result switchApp(){
        Long userId= JfCloud.getCurrent().getCurrentUserId();
        return userService.clearUserEnvCache(userId);
    }
}
