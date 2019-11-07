package com.btsoft.jf.cloud.platform.account.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdListDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.platform.account.dto.user.UserQueryDTO;
import com.btsoft.jf.cloud.platform.account.dto.user.UserSaveDTO;
import com.btsoft.jf.cloud.platform.account.dto.user.UserSelectQueryDTO;
import com.btsoft.jf.cloud.platform.account.dto.user.UserStatusUpdateDTO;
import com.btsoft.jf.cloud.platform.account.service.IUserService;
import com.btsoft.jf.cloud.platform.account.vo.user.UserBaseVO;
import com.btsoft.jf.cloud.platform.account.vo.user.UserEnvironmentVO;
import com.btsoft.jf.cloud.platform.account.vo.user.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 账号管理
 * @author jeo_cb
 * @date 2019/9/3
 **/
@RestController
@Api(tags = "用户管理")
@RequestMapping("/security/user")
@JResource(code = "user", descCN = "用户管理", descEN = "User",sort = 20)
public class UserController {

    @Autowired
    private IUserService service;

    /**
     * 分页查询账号列表
     * @author jeo_cb
     * @date 2019/9/28
     * @param  dto 查询参数
     * @return 分页列表
     **/
    @ApiOperation("分页查询账号")
    @PostMapping(ControllerConstants.Path.PAGE)
    @JOperator(code = ControllerConstants.Operator.PAGE, descCN = "用户列表", descEN = "User List",sort = 10)
    public CommonResult<PageResult<UserVO>> findRolePage(@RequestBody UserQueryDTO dto){
        return service.findUserPage(dto);
    }

    /**
     * 根据id获取单个用户
     * @author jeo_cb
     * @date 2019/9/5
     * @param  id 主键id
     * @return 用户信息
     **/
    @ApiOperation("用户详情")
    @GetMapping(ControllerConstants.Path.SINGLE)
    @JOperator(code = ControllerConstants.Operator.SINGLE, descCN = "用户详情", descEN = "User Detail",sort = 20)
    public CommonResult<UserVO> findUser(Long id){
        return service.findUser(id);
    }

    /**
     * 创建用户
     * @author jeo_cb
     * @date 2019/9/28
     * @param  dto 创建参数
     * @return 创建结果
     **/
    @JAuditLog
    @ApiOperation("保存用户")
    @PostMapping(ControllerConstants.Path.SAVE)
    @JOperator(code = ControllerConstants.Operator.SAVE, descCN = "保存用户", descEN = "Save User",sort = 30)
    public Result saveUser(@RequestBody UserSaveDTO dto){
        return service.saveUser(dto);
    }

    /**
     * 更新用户状态
     * @author jeo_cb
     * @date 2019/10/7
     * @param  dto 状态更新参数
     * @return 更新结果
     **/
    @JAuditLog
    @PutMapping("/updateStatus")
    @ApiOperation("删除、启用、锁定用户")
    @JOperator(code ="updateStatus", descCN = "删除、启用、锁定用户", descEN = "Update User Status",sort = 40)
    public Result updateUserStatus(@RequestBody UserStatusUpdateDTO dto){
        return service.updateUserStatus(dto);
    }


    /**
     * 根据用户id查询用户基本信息
     * @author jeo_cb
     * @date 2019/10/4
     * @param  dto 用户id集合
     * @return 用户基本信息集合
     **/
    @PostMapping("/userByIds")
    @ApiOperation("根据用户id获取用户集合")
    public CommonResult<List<UserBaseVO>> findUserBaseInfoList(@RequestBody BaseIdListDTO dto){
        return service.findUserBaseInfoList(dto);
    }

    /**
     * 根据用户id查询用户基本信息
     * @author jeo_cb
     * @date 2019/10/4
     * @param  dto 用户id集合
     * @return 用户基本信息Map
     **/
    @PostMapping("/userMapByIds")
    @ApiOperation("根据用户id获取用户Map")
    public CommonResult<Map<Long, UserBaseVO>> findUserBaseInfoMap(@RequestBody BaseIdListDTO dto){
        return service.findUserBaseInfoMap(dto);
    }

    /**
     * 获取用当前登录信息
     * @author jeo_cb
     * @date 2019/10/4
     * @return 获取用户当前信息
     **/
    @GetMapping("/environment")
    @ApiOperation("获取当前登录用户信息")
    public CommonResult<UserEnvironmentVO> findUserEnvironment(){
        return service.findUserEnvironment();
    }

    /**
     * 用户选择
     * @author jeo_cb
     * @date 2019/10/4
     * @return 用户列表
     **/
    @PostMapping("/select")
    @ApiOperation("根据用户名称搜索用户")
    public CommonResult<List<UserBaseVO>> findSelectUserList(@RequestBody UserSelectQueryDTO dto){
        return service.findSelectUserList(dto);
    }

    /**
     * 清除用户当前信息缓存
     * @author jeo_cb
     * @date 2019/11/4
     * @param  dto 用户id
     * @return 清除结果
     **/
    @JAuditLog
    @PostMapping("/clearUserEnvCache")
    @ApiOperation("清除用户环境缓存")
    @JOperator(code ="clearUserEnvCache", descCN = "清除用户环境缓存", descEN = "Clear User Env Cache",sort = 50)
    public Result clearUserEnvCache(@RequestBody BaseIdDTO dto){
        return service.clearUserEnvCache(dto.getId());
    }
}
