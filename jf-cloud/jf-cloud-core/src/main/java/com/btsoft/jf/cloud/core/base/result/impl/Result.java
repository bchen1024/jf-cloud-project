package com.btsoft.jf.cloud.core.base.result.impl;

import com.btsoft.jf.cloud.core.base.result.IResult;

/**
 * 无返回值通用结果
 * @author jeo_cb
 * @date 2019/8/30
 **/
public class Result implements IResult {

    private String code;
    private String msg;
    private Boolean success;

    public Result() {
    }

    public Result(String code, String msg, Boolean success) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
