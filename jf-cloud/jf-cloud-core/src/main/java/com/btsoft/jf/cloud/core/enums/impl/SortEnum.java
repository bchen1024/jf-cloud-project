package com.btsoft.jf.cloud.core.enums.impl;

import com.btsoft.jf.cloud.core.enums.IBaseEnum;

/**
 * 排序枚举
 * @author jeo_cb
 * @date 2019/8/28
 **/
public enum SortEnum implements IBaseEnum{

    /** 升序 */
    Asc(0L,"asc","升序","Asc"),

    /** 降序 */
    Desc(1L,"desc","降序","Desc");

    private Long value;
    private String key;
    private String cnName;
    private String enName;

    SortEnum(Long value, String key, String cnName, String enName) {
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
