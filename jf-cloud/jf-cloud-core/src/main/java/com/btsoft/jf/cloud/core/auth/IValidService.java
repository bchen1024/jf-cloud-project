package com.btsoft.jf.cloud.core.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求校验服务接口
 * @author jeo_cb
 * @date 2019/8/30
 **/
public interface IValidService {

    /**
     * 校验请求合法性
     * @author jeo_cb
     * @date 2019/8/30
     * @param  req 请求
     * @param  resp 响应
     * @return 校验结果
     **/
    boolean valid(HttpServletRequest req, HttpServletResponse resp);

    /**
     * 校验顺序
     * @author jeo_cb
     * @date 2019/8/30
     * @return 排序
     **/
    int sort();
}
