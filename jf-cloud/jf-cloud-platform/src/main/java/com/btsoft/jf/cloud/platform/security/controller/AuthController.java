package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.base.dto.impl.ValidateCodeDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.auth.AccountLoginDTO;
import com.btsoft.jf.cloud.platform.security.dto.auth.MobileLoginDTO;
import com.btsoft.jf.cloud.platform.security.service.IAuthService;
import com.btsoft.jf.cloud.platform.security.vo.auth.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 鉴权Controller,包含登录，登出等服务
 * @author jeo_cb
 * @date 2019/9/3
 **/
@RestController
@RequestMapping("/auth")
@Api(tags = "鉴权服务")
public class AuthController {

    @Autowired
    private IAuthService service;

    /**
     * 账号密码登录
     * @author jeo_cb
     * @date 2019/10/14
     * @param  dto 登录参数
     * @return token
     **/
    @ApiOperation("账号密码登录")
    @PostMapping("/account/login")
    public CommonResult<LoginVO> accountLogin(@RequestBody @Valid AccountLoginDTO dto){
        return service.accountLogin(dto);
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
        return service.logout();
    }

    /**
     * 获取验证码
     * @author jeo_cb
     * @date 2019/10/24
     * @param resp 响应
     **/
    @ApiOperation("获取验证码图片")
    @GetMapping("/validateCode")
    public void generateValidateCode(ValidateCodeDTO dto, HttpServletResponse resp){
        /*ValidateCodeDTO dto=new ValidateCodeDTO();
        dto.setType(type);
        dto.setKey(key);
        dto.setWidth(width);
        dto.setHeight(height);
        dto.setLineNum(lineNum);
        dto.setDotRate(dotRate);*/
        service.generateValidateCode(dto,resp);
    }
}
