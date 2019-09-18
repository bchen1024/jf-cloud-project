package com.btsoft.jf.cloud.core.enums.impl;

import com.btsoft.jf.cloud.core.enums.IBaseEnum;

/**
 * 环境枚举
 * @author jeo_cb
 * @date 2019/8/29
 **/
public enum EnvironmentEnum implements IBaseEnum {

    /** 开发环境 */
    Dev(1L,"dev","开发环境","Development Environment"),
    /** 测试环境 */
    Test(2L,"test","测试环境","Test Environment"),
    /** 生产环境 */
    Pro(3L,"pro","生产环境","Production Environment");

    private Long value;
    private String key;
    private String cnName;
    private String enName;

    EnvironmentEnum(Long value, String key, String cnName, String enName) {
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
