package com.btsoft.jf.cloud.core.constant;

/**
 * 错误码常量接口
 * @author jeo_cb
 * @date 2019/8/30
 **/
public interface ErrorCodeConstants {

    /**
     * 全局常量
     **/
    interface Global{

        /** profile配置是非法的，必须包含dev、test、pro其中任意一项*/
        String PROFILE_IS_ILLEGAL="profile.illegal";

        /** profile配置错误*/
        String PROFILE_IS_WRONG="profile.isWrong";

        /** 服务异常*/
        String SERVER_ERROR="server.error";

        String SUCCESS="server.success";
    }

    /**
     * 鉴权相关
     **/
    interface Auth{
        /** 鉴权失败*/
        String AUTH_FAIL="auth.fail";
        /** 无Token*/
        String AUTH_NO_TOKEN="auth.noToken";
    }
}
