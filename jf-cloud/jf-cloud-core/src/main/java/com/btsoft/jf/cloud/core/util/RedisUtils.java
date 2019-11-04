package com.btsoft.jf.cloud.core.util;

/**
 * Redis工具类
 * @author jeo_cb
 * @date 2019/10/28 19:07
 **/
public class RedisUtils {

    public static String getkey(Object... keys){
        if(keys==null || keys.length==0){
            return null;
        }
        StringBuilder result=new StringBuilder();
        for (Object key : keys) {
            result.append(key);
            result.append(":");
        }
        return result.substring(0,result.length()-1);
    }
}
