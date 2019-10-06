package com.btsoft.jf.cloud.platform.security.enums;

import com.btsoft.jf.cloud.core.enums.IBaseEnum;
import com.btsoft.jf.cloud.core.enums.impl.LanguageEnum;

/**
 * 用户状态
 * @author jeo_cb
 * @date 2019/9/28
 **/
public enum UserStatusEnum implements IBaseEnum {
    /** 正常 */
    Y(1L,"Y","正常","Normal"),
    /** 锁定 */
    L(2L,"L","锁定","Lock"),
    /** 删除*/
    D(3L,"D","删除","Delete");

    UserStatusEnum(Long value, String key, String cnName, String enName) {
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
