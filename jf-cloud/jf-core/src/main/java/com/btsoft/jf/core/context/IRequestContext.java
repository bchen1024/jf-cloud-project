package com.btsoft.jf.core.context;

import java.io.Serializable;

/**
 * 请求上下文接口
 * @author jeo_cb
 * @date 2019/8/29
 **/
public interface IRequestContext extends Serializable {

    /**
     * 获取Token
     * @author jeo_cb
     * @date 2019/8/29
     * @return Token值
     **/
    String getToken();

    /**
     * 获取请求的应用
     * @author jeo_cb
     * @date 2019/8/29
     * @return 应用编码
     **/
    String getRequestApp();

    /**
     * 获取语言
     * @author jeo_cb
     * @date 2019/8/29
     * @return 语言
     **/
    String getLanguage();

    /**
     * 获取当前登录着id
     * @author jeo_cb
     * @date 2019/8/29
     * @return 当前登录着id
     **/
    Long getCurrentUserId();

    /**
     * 获取请求的IP
     * @author jeo_cb
     * @date 2019/9/4
     * @return 请求IP
     **/
    String getRequestIp();

    /**
     * 获取SessionId
     * @author jeo_cb
     * @date 2019/9/4
     * @return SessionId
     **/
    String getSessionId();

    /**
     * 获取当前访问的APP
     * @author jeo_cb
     * @date 2019/10/8
     * @return 当前访问的应用编码
     **/
    String getAppCode();
}
