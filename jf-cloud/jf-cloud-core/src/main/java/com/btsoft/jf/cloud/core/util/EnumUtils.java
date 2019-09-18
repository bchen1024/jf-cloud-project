package com.btsoft.jf.cloud.core.util;

import java.util.Arrays;
import java.util.List;

/**
 * 枚举工具类
 * @author jeo_cb
 * @date 2019/8/28
 **/
public class EnumUtils {

    /**
     * 根据Class类型获取枚举集合
     * @author jeo_cb
     * @date 2019/8/28
     * @param clazz 枚举Class
     * @return 枚举集合
     **/
    public static <T> List<T> getEnums(Class<T> clazz){
        T[] enums=clazz.getEnumConstants();
        return Arrays.asList(enums);
    }
}
