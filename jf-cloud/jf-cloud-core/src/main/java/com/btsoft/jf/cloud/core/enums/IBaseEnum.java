package com.btsoft.jf.cloud.core.enums;

/**
 * 基类枚举接口
 * @author jeo_cb
 * @date 2019/8/28
 **/
public interface IBaseEnum{

    /**
     * 获取Value值
     * @author jeo_cb
     * @date 2019/8/28
     * @return Value值
     **/
    Long getValue();

    /**
     * 获取Key值
     * @author jeo_cb
     * @date 2019/8/28
     * @return Key值
     **/
    String getKey();

    /**
     * 获取Value值
     * @author jeo_cb
     * @date 2019/8/28
     * @return Value值
     **/
    String getCnName();

    /**
     * 获取Value值
     * @author jeo_cb
     * @date 2019/8/28
     * @return Value值
     **/
    String getEnName();

    /**
     * 获取Value值
     * @author jeo_cb
     * @date 2019/8/28
     * @param language 语言
     * @return Value值
     **/
    String getName(String language);
}
