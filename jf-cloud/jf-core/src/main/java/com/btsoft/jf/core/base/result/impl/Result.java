package com.btsoft.jf.core.base.result.impl;

import com.btsoft.jf.core.base.result.IResult;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 结果实现类
 * @author jeo_cb
 * @date 2020/1/12
 **/
public class Result implements IResult {

    private static final long serialVersionUID = 873306720045600017L;
    private String code;
    private Object[] args;
    private String msg;
    private boolean success;

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
