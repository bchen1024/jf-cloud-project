package com.btsoft.jf.cloud.core.base.dto;

import java.io.Serializable;

/**
 * 基类分页接口
 * @author jeo_cb
 * @date 2019/8/29
 **/
public interface IBasePage extends Serializable {

    /**
     * 获取每页大小
     * @author jeo_cb
     * @date 2019/8/29
     * @return 每页大小
     **/
    Integer getPageSize();

    /**
     * 获取当前第几页
     * @author jeo_cb
     * @date 2019/8/29
     * @return 当前页码
     **/
    Integer getCurPage();
}
