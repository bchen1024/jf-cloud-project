package com.btsoft.jf.cloud.core.base.dto;

import java.io.Serializable;
/**
 * 基类排序接口
 * @author jeo_cb
 * @date 2019/8/29
 **/
public interface IBaseSort  extends Serializable {

    /**
     * 获取排序字段
     * @author jeo_cb
     * @date 2019/8/29
     * @return 排序字段
     **/
    String getField();

    /**
     * 获取排序方式，asc|desc
     * @author jeo_cb
     * @date 2019/8/29
     * @return 排序方式
     **/
    String getSort();
}
