package com.btsoft.jf.cloud.platform.security.enums;

import com.btsoft.jf.cloud.core.enums.IBaseEnum;
import com.btsoft.jf.cloud.core.enums.impl.LanguageEnum;

/**
 * 权限查询类型枚举
 * @author jeo_cb
 * @date 2019/10/10
 **/
public enum PermissionQueryTypeEnum implements IBaseEnum {
    /** 角色权限 */
    Role(1L,"rolePermission","角色权限","Role Permission");

    PermissionQueryTypeEnum(Long value, String key, String cnName, String enName) {
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
