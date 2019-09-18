package com.btsoft.jf.cloud.core.base.result;

/**
 * 通用结果接口
 * @author jeo_cb
 * @date 2019/8/30
 **/
public interface ICommonResult<T>  extends IResult {

    /**
     * 获取结果数据
     * @author jeo_cb
     * @date 2019/8/29
     * @return 结果数据
     **/
    T getData();
}
