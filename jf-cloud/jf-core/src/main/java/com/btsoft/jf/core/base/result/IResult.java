package com.btsoft.jf.core.base.result;

import java.io.Serializable;

/**
 * 结果接口
 * @author jeo_cb
 * @date 2020/1/12
 **/
public interface IResult extends Serializable {

    /**
     * 获取错误码
     * @author jeo_cb
     * @date 2020/1/12
     * @return 错误码
     **/
    String getCode();
    /**
     * 获取错误码参数
     * @author jeo_cb
     * @date 2020/1/12
     * @return 错误码参数
     **/
    Object[] getArgs();

    /**
     * 获取返回消息
     * @author jeo_cb
     * @date 2020/1/12
     * @return 返回消息
     **/
    String getMsg();

    /**
     * 是否成功
     * @author jeo_cb
     * @date 2020/1/12
     * @return 是否成功
     **/
    boolean isSuccess();
}
