package com.btsoft.jf.cloud.core.enums.impl;

import com.btsoft.jf.cloud.core.enums.IBaseEnum;

/**
 * 输入插入方式
 * @author jeo_cb
 * @date 2019/11/21
 **/
public enum InsertTypeEnum implements IBaseEnum {

    /** 普通插入 */
    Insert(0L,"INSERT","插入","Insert");

    private Long value;
    private String key;
    private String cnName;
    private String enName;

    InsertTypeEnum(Long value, String key, String cnName, String enName) {
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
