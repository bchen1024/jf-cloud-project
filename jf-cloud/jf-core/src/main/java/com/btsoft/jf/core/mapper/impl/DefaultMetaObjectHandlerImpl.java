package com.btsoft.jf.core.mapper.impl;

import com.btsoft.jf.core.mapper.IMetaObjectHandler;
import com.btsoft.jf.core.mapper.enums.MapperMethod;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 默认的字段处理实现，实现公共字段填充
 * @author jeo_cb
 * @date 2020/1/19 11:44
 **/
@Component
public class DefaultMetaObjectHandlerImpl implements IMetaObjectHandler {
    @Override
    public void handler(Object obj, MapperMethod mapperMethod) {
        //插入
        if(MapperMethod.Insert==mapperMethod){
            Class<?> clazz=obj.getClass();
            try {
                Method method=clazz.getMethod("setCreateBy",Long.TYPE);
                method.invoke(obj,1L);
            }catch (Exception ex){

            }
        }
    }
}
