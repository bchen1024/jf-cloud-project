package com.btsoft.jf.core.exception;

/**
 * 通用异常
 * @author jeo_cb
 * @date 2019/8/30
 **/
public class CommonException extends RuntimeException {

    private String code;

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String code,String message) {
        super(message);
        this.code = code;
    }

    public CommonException(String code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CommonException(String code,Throwable cause) {
        super(cause);
        this.code = code;
    }

    public CommonException(String code,String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
