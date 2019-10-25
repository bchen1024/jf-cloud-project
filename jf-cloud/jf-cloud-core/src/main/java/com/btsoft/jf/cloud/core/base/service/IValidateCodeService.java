package com.btsoft.jf.cloud.core.base.service;

import com.btsoft.jf.cloud.core.base.dto.impl.ValidateCodeDTO;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * 验证码生成Service接口
 * @author jeo_cb
 * @date 2019/10/24 17:23
 **/
public interface IValidateCodeService {

    /**
     * 生成默认的随机验证码
     * @author jeo_cb
     * @date 2019/10/25
     * @return 验证码
     */
    String generateRandomCode();

    /**
     * 生成指定长度随机验证码
     * @author jeo_cb
     * @date 2019/10/25
     * @param codeLength 验证码长度
     * @return 验证码
     */
    String generateRandomCode(int codeLength);

    /**
     * 获取验证码图像
     * @author jeo_cb
     * @param code 验证码
     * @date 2019/10/24
     * @return 验证码图像
     */
    BufferedImage generateValidateCodeImage(String code);

    /**
     * 获取验证码图像
     * @author jeo_cb
     * @date 2019/10/24
     * @param code 验证码
     * @param width 宽度
     * @param height 高度
     * @param lineNum 干扰线数量
     * @param dotRate 噪点率
     * @return 验证码图像
     */
    BufferedImage generateValidateCodeImage(String code, int width, int height, int lineNum, float dotRate);

    /**
     * 写入验证图像到响应
     * @author jeo_cb
     * @date 2019/10/25
     * @param resp 响应
     * @return 验证码
     */
    String writeValidateCodeImage(HttpServletResponse resp);

    /**
     * 根据参数写入验证图像到响应
     * @author jeo_cb
     * @date 2019/10/25
     * @param resp 响应
     * @param dto 验证码参数
     * @return 验证码
     */
    String writeValidateCodeImage(HttpServletResponse resp, ValidateCodeDTO dto);
}
