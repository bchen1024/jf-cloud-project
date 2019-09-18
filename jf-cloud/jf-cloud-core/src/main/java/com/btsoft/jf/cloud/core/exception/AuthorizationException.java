package com.btsoft.jf.cloud.core.exception;

/**
 * 鉴权异常
 * @author jeo_cb
 * @date 2019/8/30
 **/
public class AuthorizationException extends CommonException{
    public AuthorizationException(String code) {
        super(code);
    }

    public AuthorizationException(String code, String message) {
        super(code, message);
    }

    public AuthorizationException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public AuthorizationException(String code, Throwable cause) {
        super(code, cause);
    }

    public AuthorizationException(String code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(code, message, cause, enableSuppression, writableStackTrace);
    }
}
