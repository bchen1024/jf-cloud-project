package com.btsoft.jf.core.base.result.impl;

import com.btsoft.jf.core.base.result.ICommonResult;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 通用结果
 * @author jeo_cb
 * @date 2020/1/13
 **/
public class CommonResult<T> extends Result implements ICommonResult<T> {

    private static final long serialVersionUID = 5104679181220824490L;
    private T data;

    @Override
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
