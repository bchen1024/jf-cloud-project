package com.btsoft.jf.cloud.platform.security.enums;

import com.btsoft.jf.cloud.core.enums.IBaseEnum;
import com.btsoft.jf.cloud.core.enums.impl.LanguageEnum;

/**
 * 应用类型枚举
 * @author jeo_cb
 * @date 2019/9/28
 **/
public enum AppTypeEnum implements IBaseEnum {
    /** 系统应用 */
    System(1L,"System","系统应用","System"),
    /** 业务应用 */
    Business(2L,"Business","Business","Business");

    AppTypeEnum(Long value, String key, String cnName, String enName) {
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
