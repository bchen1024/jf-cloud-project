package com.btsoft.jf.cloud.core.enums.impl;

import com.btsoft.jf.cloud.core.enums.IBaseEnum;

/**
 * 操作类型枚举
 * @author jeo_cb
 * @date 2019/9/1
 **/
public enum  OperationTypeEnum implements IBaseEnum {

    /** 保存 */
    Save(1L,"save","保存","Save"),
    /** 删除 */
    Delete(2L,"delete","删除","Delete"),
    /** 创建 */
    Create(3L,"create","创建","Create"),
    /** 更新 */
    Update(4L,"update","更新","Update"),
    /** 操作 */
    Operate(5L,"operate","操作","Operate"),
    /** 同步 */
    Sync(6L,"sync","同步","Sync");

    private Long value;
    private String key;
    private String cnName;
    private String enName;

    OperationTypeEnum(Long value, String key, String cnName, String enName) {
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
