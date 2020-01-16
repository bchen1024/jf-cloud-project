package com.btsoft.jf.core.base.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 批量实体
 * @author jeo_cb
 * @date 2020/1/13
 **/
public interface IBatchEntity<T> extends Serializable {

    /**
     * 获取实体列表
     * @author jeo_cb
     * @date 2020/1/13
     * @return 实体列表
     **/
    List<T> getList();
}
