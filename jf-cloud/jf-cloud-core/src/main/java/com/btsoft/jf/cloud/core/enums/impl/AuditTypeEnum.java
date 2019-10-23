package com.btsoft.jf.cloud.core.enums.impl;

import com.btsoft.jf.cloud.core.enums.IBaseEnum;

/**
 * 审计类型枚举
 * @author jeo_cb
 * @date 2019/8/29
 **/
public enum AuditTypeEnum implements IBaseEnum {

    /** 审计 */
    Audit(1L,"audit","审计","Audit"),
    /** 性能 */
    Performance(2L,"performance","性能","Performance");

    private Long value;
    private String key;
    private String cnName;
    private String enName;

    AuditTypeEnum(Long value, String key, String cnName, String enName) {
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
