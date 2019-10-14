package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.auth.AccountLoginDTO;
import com.btsoft.jf.cloud.platform.security.dto.auth.MobileLoginDTO;
import com.btsoft.jf.cloud.platform.security.vo.auth.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 鉴权Controller,包含登录，登出等服务
 * @author jeo_cb
 * @date 2019/9/3
 **/
@RestController
@RequestMapping("/auth")
@Api(tags = "鉴权服务")
public class AuthController {

    /**
     * 账号密码登录
     * @author jeo_cb
     * @date 2019/10/14
     * @param  dto 登录参数
     * @return token
     **/
    @ApiOperation("账号密码登录")
    @PostMapping("/account/login")
    public CommonResult<LoginVO> accountLogin(@RequestBody AccountLoginDTO dto){
        return null;
    }

    /**
     * 手机验证码登录
     * @author jeo_cb
     * @date 2019/10/14
     * @param  dto 登录参数
     * @return token
     **/
    @ApiOperation("手机验证码登录")
    @PostMapping("/mobile/login")
    public CommonResult<LoginVO> mobileLogin(@RequestBody MobileLoginDTO dto){
        return null;
    }

    /**
     * 登出
     * @author jeo_cb
     * @date 2019/10/14
     * @return 登出结果
     **/
    @ApiOperation("登出")
    @PostMapping("/logout")
    public Result logout(){
        return null;
    }
}
