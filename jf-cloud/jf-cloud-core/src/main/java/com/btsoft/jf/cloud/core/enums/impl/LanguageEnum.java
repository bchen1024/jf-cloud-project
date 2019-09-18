package com.btsoft.jf.cloud.core.enums.impl;

import com.btsoft.jf.cloud.core.enums.IBaseEnum;

/**
 * 语言枚举
 * @author jeo_cb
 * @date 2019/8/28
 **/
public enum LanguageEnum implements IBaseEnum{
    /** 中文 */
    CN(0L,"zh-CN","中文","Chinese"),
    /** 英文 */
    EN(1L,"en-US","英文","English");

    private Long value;
    private String key;
    private String cnName;
    private String enName;

    LanguageEnum(Long value, String key, String cnName, String enName) {
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
