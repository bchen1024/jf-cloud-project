package com.btsoft.jf.cloud.platform.security.vo;

import java.io.Serializable;

/**
 * 应用VO接口
 * @author jeo_cb
 * @date 2019/11/5
 **/
public interface IAppVO extends Serializable {

    /**
     * 获取应用id
     * @author jeo_cb
     * @date 2019/11/5
     * @return 应用id
     */
   Long getAppId();
    /**
     * 设置应用编码
     * @author jeo_cb
     * @date 2019/11/5
     * @param appCode 应用编码
     */
   void setAppCode(String appCode);

    /**
     * 设置应用名称
     * @author jeo_cb
     * @date 2019/11/5
     * @param appName 应用名称
     */
   void setAppName(String appName);

    /**
     * 设置应用描述
     * @author jeo_cb
     * @date 2019/11/5
     * @param appDesc 应用描述
     */
   void setAppDesc(String appDesc);

}
