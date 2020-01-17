package com.btsoft.jf.core.pojo.result;

/**
 * 通用结果接口
 * @author jeo_cb
 * @date 2020/1/12
 **/
public interface ICommonResult<T> extends IResult {

    /**
     * 获取数据
     * @author jeo_cb
     * @date 2020/1/13
     * @return 结果
     **/
    T getData();
}
