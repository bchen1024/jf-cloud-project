package com.btsoft.jf.cloud.platform.auth.service;

import com.btsoft.jf.cloud.core.base.dto.impl.ValidateCodeDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.auth.dto.AccountLoginDTO;
import com.btsoft.jf.cloud.platform.auth.vo.LoginVO;

import javax.servlet.http.HttpServletResponse;

/**
 * 鉴权Service接口
 * @author jeo_cb
 * @date 2019/10/25 11:26
 **/
public interface IAuthService {

    /**
     * @author jeo_cb
     * @date 2019/10/25
     * @param dto 参数
     * @param resp 响应
     */
    void generateValidateCode(ValidateCodeDTO dto, HttpServletResponse resp);

    /**
     * 账号密码登录
     * @author jeo_cb
     * @date 2019/10/14
     * @param  dto 登录参数
     * @return token
     **/
    CommonResult<LoginVO> accountLogin(AccountLoginDTO dto);

    /**
     * 登出
     * @author jeo_cb
     * @date 2019/10/25
     * @return 登出结果
     */
    Result logout();
}
