package com.btsoft.jf.cloud.core.constant;

/**
 * 环境变量常量接口
 * @author jeo_cb
 * @date 2019/8/29
 **/
public interface EnvironmentConstants {

    /**
     * Spring相关常量
     * @author jeo_cb
     * @date 2019/8/29
     **/
    interface Spring{
        /** spring当前环境属性key */
        String PROPERTY_KEY_PROFILES="spring.profiles.active";

        /** 当前请求ip */
        String SPRING_CLOUD_CLIENT_IP="spring.cloud.client.ip-address";

        /** 当前应用code */
        String SPRING_APPLICATION_NAME="spring.application.name";

    }

    interface  Jf{
        /** 当前应用code */
        String JF_CLOUD_APPCODE="jf.cloud.appCode";
    }
}
