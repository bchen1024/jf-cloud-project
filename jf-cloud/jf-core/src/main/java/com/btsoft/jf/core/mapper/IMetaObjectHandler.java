package com.btsoft.jf.core.mapper;

import com.btsoft.jf.core.mapper.enums.MapperMethod;

/**
 * 字段处理接口
 * @author jeo_cb
 * @date 2020/1/19 11:42
 **/
public interface IMetaObjectHandler {

    /**
     * 处理字段
     * @author jeo_cb
     * @date 2020/1/19
     * @param obj 参数
     */
    void handler(Object obj, MapperMethod mapperMethod);
}
