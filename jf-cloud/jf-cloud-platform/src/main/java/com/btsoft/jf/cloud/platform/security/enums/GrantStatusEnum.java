package com.btsoft.jf.cloud.platform.security.enums;

import com.btsoft.jf.cloud.core.enums.IBaseEnum;
import com.btsoft.jf.cloud.core.enums.impl.LanguageEnum;

/**
 * 用户授权状态
 * @author jeo_cb
 * @date 2019/9/28
 **/
public enum GrantStatusEnum implements IBaseEnum {
    /** 未生效 */
    NotEffective(1L,"NotEffective","未生效","Not Effective"),
    /** 正常 */
    Normal(2L,"Normal","正常","Normal"),
    /** 即将过期*/
    AboutToExpire(3L,"AboutToExpire","即将过期","About To Expire"),
    /** 已过期*/
    Expired(4L,"Expired","已过期","Expired");

    GrantStatusEnum(Long value, String key, String cnName, String enName) {
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
