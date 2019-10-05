package com.btsoft.jf.cloud.core.enums.impl;

import com.btsoft.jf.cloud.core.enums.IBaseEnum;

/**
 * 权限类型枚举
 * @author jeo_cb
 * @date 2019/10/5
 **/
public enum PermissionTypeEnum implements IBaseEnum{
    /** 中文 */
    Resources(0L,"resources","资源","Resources"),
    /** 英文 */
    Method(1L,"method","方法","Method");

    private Long value;
    private String key;
    private String cnName;
    private String enName;

    PermissionTypeEnum(Long value, String key, String cnName, String enName) {
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
