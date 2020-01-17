package com.btsoft.jf.core.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Class工具类
 * @author jeo_cb
 * @date 2020/1/17 9:21
 **/
public class ClassUtils {

    /**
     * 获取class泛型
     * @author jeo_cb
     * @date 2020/1/17
     * @param clazz class类
     * @return class泛型
     */
    public static ParameterizedType getParameterizedType(Class<?> clazz) {
        return (ParameterizedType)clazz.getGenericSuperclass();
    }

    /**
     * 根据索引获取class泛型
     * @author jeo_cb
     * @date 2020/1/17
     * @param clazz class类
     * @return class泛型
     */
    public static Type getParameterizedType(Class<?> clazz,int index){
        ParameterizedType parameterizedType = getParameterizedType(clazz);
        return parameterizedType.getActualTypeArguments()[index];
    }
}
