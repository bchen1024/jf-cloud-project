package com.btsoft.jf.cloud.core.util;

import com.alibaba.fastjson.JSON;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.RequestConstants;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 响应工具类
 * @author jeo_cb
 * @date 2019/8/30
 **/
public class ResponseUtils {

    public static void writeException(HttpServletResponse resp, String code, Exception ex){
        Result result=CommonResultUtils.fail(code,ex);
        resp.setCharacterEncoding(RequestConstants.Common.UTF8);
        resp.setContentType("text/application; charset=utf-8");
        try(PrintWriter pw=resp.getWriter()){
            pw.write(JSON.toJSONString(result));
            pw.flush();
            pw.close();
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
