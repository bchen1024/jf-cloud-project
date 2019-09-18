package com.btsoft.jf.cloud.core.base.result.impl;

import com.btsoft.jf.cloud.core.base.result.ICommonResult;

/**
 * 通用结果
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class CommonResult<T> extends Result implements ICommonResult<T> {

    private T data;

    @Override
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
