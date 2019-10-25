package com.btsoft.jf.cloud.core.enums.impl;

import com.btsoft.jf.cloud.core.enums.IBaseEnum;

/**
 * 验证码类型枚举
 * @author jeo_cb
 * @date 2019/8/29
 **/
public enum ValidateCodeTypeEnum implements IBaseEnum {

    /** 登录验证 */
    LoginValid(1L,"LoginValid","登录验证","Login Valid"),
    /** 性能 */
    UpdatePwd(2L,"UpdatePwd","更新密码验证","Update Password");

    private Long value;
    private String key;
    private String cnName;
    private String enName;

    ValidateCodeTypeEnum(Long value, String key, String cnName, String enName) {
        this.value = value;
        this.key = key;
        this.cnName = cnName;
        this.enName = enName;
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getCnName() {
        return cnName;
    }

    @Override
    public String getEnName() {
        return enName;
    }

    @Override
    public String getName(String language) {
        if(LanguageEnum.EN.getKey().equals(language)){
            return enName;
        }
        return cnName;
    }
}
