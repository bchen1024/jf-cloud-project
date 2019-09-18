package com.btsoft.jf.cloud.core.constant;

/**
 * 请求常量
 * @author jeo_cb
 * @date 2019/8/29
 **/
public interface RequestConstants {

    interface Header{
        /** 鉴权*/
        String AUTHORIZATION="Authorization";

        /** Token*/
        String TOKEN="Token";

        /** 语言*/
        String LANGUAGE="language";

        /** 当前语言*/
        String CURRENT_LANGUAGE="currentLanguage";

        /** 网关标识*/
        String X_CLOUD_GATEWAY="x-cloud-gateway";

        /** 用户信息*/
        String X_CLOUD_USER="x-cloud-user";

        /** 用户id*/
        String X_CLOUD_UID="x-cloud-uid";

        /** 应用编码*/
        String X_CLOUD_APP="x-cloud-app";
    }

    interface Url{
        /** Token*/
        String TOKEN="token";

        /** 网关上下文*/
        String GATEWAY_CONTEXT_PATH="/jfcloud";

        String IGNORE_URL="/public,/auth,/actuator,swagger,.html,.css,.js,.jpg,.png,.gif";
    }

    interface Common{
        /** UTF-8*/
        String UTF8="UTF-8";
    }
}
