package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdListDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.user.UserCreateDTO;
import com.btsoft.jf.cloud.platform.security.dto.user.UserQueryDTO;
import com.btsoft.jf.cloud.platform.security.service.IUserService;
import com.btsoft.jf.cloud.platform.security.vo.user.UserBaseVO;
import com.btsoft.jf.cloud.platform.security.vo.user.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 账号管理
 * @author jeo_cb
 * @date 2019/9/3
 **/
@RestController
@RequestMapping("/security/user")
@Api(tags = "账号管理")
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
    @PostMapping("/page")
    @ApiOperation("分页查询账号")
    public CommonResult<PageResult<UserVO>> findRolePage(@RequestBody UserQueryDTO dto){
        return service.findUserPage(dto);
    }

    /**
     * 创建用户
     * @author jeo_cb
     * @date 2019/9/28
     * @param  dto 创建参数
     * @return 创建结果
     **/
    @PostMapping("/create")
    @ApiOperation("创建用户")
    public Result createUser(@RequestBody UserCreateDTO dto){
        return service.createUser(dto);
    }


    @PostMapping("/userByIds")
    @ApiOperation("根据用户id获取用户集合")
    public CommonResult<List<UserBaseVO>> findUserBaseInfoList(@RequestBody BaseIdListDTO dto){
        return service.findUserBaseInfoList(dto);
    }

    @PostMapping("/userMapByIds")
    @ApiOperation("根据用户id获取用户Map")
    public CommonResult<Map<Long, UserBaseVO>> findUserBaseInfoMap(@RequestBody BaseIdListDTO dto){
        return service.findUserBaseInfoMap(dto);
    }
}
