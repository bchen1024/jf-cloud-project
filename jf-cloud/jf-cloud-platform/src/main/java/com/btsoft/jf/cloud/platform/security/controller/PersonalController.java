package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import com.btsoft.jf.cloud.platform.security.dto.auth.UpdatePasswordDTO;
import com.btsoft.jf.cloud.platform.security.service.IUserService;
import com.btsoft.jf.cloud.platform.security.vo.user.UserVO;
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

    /**
     * 用户基本信息
     * @author jeo_cb
     * @date 2019/10/29
     * @return 用户基本信息
     **/
    @ApiOperation("基本信息")
    @GetMapping("/baseInfo")
    @JOperator(code = "baseInfo", descCN = "基本信息", descEN = "Base Info",sort = 10)
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
    @JOperator(code = "updatePassword", descCN = "修改密码", descEN = "Update Password",sort = 20)
    public Result updatePassword(@RequestBody @Valid UpdatePasswordDTO dto){
        return userService.updatePassword(dto);
    }
}
