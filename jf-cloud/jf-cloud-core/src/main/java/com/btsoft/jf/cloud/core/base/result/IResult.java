package com.btsoft.jf.cloud.core.base.result;

import java.io.Serializable;

/**
 * 结果接口
 * @author jeo_cb
 * @date 2019/8/29
 **/
public interface IResult extends Serializable {

    /**
     * 获取结果编码
     * @author jeo_cb
     * @date 2019/8/29
     * @return 结果编码
     **/
    String getCode();

    /**
     * 获取结果消息
     * @author jeo_cb
     * @date 2019/8/29
     * @return 消息
     **/
    String getMsg();

    /**
     * 获取成功标识
     * @author jeo_cb
     * @date 2019/8/29
     * @return 成功标识
     **/
    Boolean getSuccess();
}
