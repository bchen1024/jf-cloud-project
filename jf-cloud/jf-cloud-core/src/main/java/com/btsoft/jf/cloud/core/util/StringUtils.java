package com.btsoft.jf.cloud.core.util;

import com.btsoft.jf.cloud.core.constant.NumberConstants;

/**
 * 字符串工具类
 * @author jeo_cb
 * @date 2019/9/29
 **/
public class StringUtils {

    public static String zeroFill(int num){
        if(num>0 && num< NumberConstants.TEN){
            return "0"+num;
        }
        return String.valueOf(num);
    }
}
