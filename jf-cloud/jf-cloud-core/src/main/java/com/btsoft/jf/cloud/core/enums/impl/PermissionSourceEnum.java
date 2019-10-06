package com.btsoft.jf.cloud.core.enums.impl;

import com.btsoft.jf.cloud.core.enums.IBaseEnum;

/**
 * 权限来源枚举
 * @author jeo_cb
 * @date 2019/10/6
 **/
public enum PermissionSourceEnum implements IBaseEnum{
    /** 中文 */
    Annotation(0L,"annotation","注解","Annotation"),
    /** 英文 */
    Custom(1L,"custom","自定义","Custom");

    private Long value;
    private String key;
    private String cnName;
    private String enName;

    PermissionSourceEnum(Long value, String key, String cnName, String enName) {
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
