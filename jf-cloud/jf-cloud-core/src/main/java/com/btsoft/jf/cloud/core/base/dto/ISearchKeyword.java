package com.btsoft.jf.cloud.core.base.dto;

import java.io.Serializable;

/**
 * 搜索关键字接口
 * @author jeo_cb
 * @date 2019/9/7
 **/
public interface ISearchKeyword extends Serializable {

    /**
     * 获取关键字字段
     * @author jeo_cb
     * @date 2019/9/7
     * @return 关键字字段
     **/
    String getKeywordField();

    /**
     * 获取关键字值
     * @author jeo_cb
     * @date 2019/9/7
     * @return 关键字值
     **/
    Object getKeywordValue();
}
