package com.btsoft.jf.cloud.platform.security.enums;

import com.btsoft.jf.cloud.core.enums.IBaseEnum;
import com.btsoft.jf.cloud.core.enums.impl.LanguageEnum;

/**
 * 用户类型枚举
 * @author jeo_cb
 * @date 2019/9/28
 **/
public enum UserTypeEnum implements IBaseEnum {
    /** 员工 */
    Employee(1L,"employee","员工","Employee"),
    /** 注册用户 */
    Register(2L,"register","注册用户","Register");

    UserTypeEnum(Long value, String key, String cnName, String enName) {
        this.value = value;
        this.key = key;
        this.cnName = cnName;
        this.enName = enName;
    }

    private Long value;
    private String key;
    private String cnName;
    private String enName;

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
