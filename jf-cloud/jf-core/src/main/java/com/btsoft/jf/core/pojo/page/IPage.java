package com.btsoft.jf.core.pojo.page;

import java.io.Serializable;

/**
 * 分页接口
 * @author jeo_cb
 * @date 2020/1/13
 **/
public interface IPage extends Serializable {

    /**
     * 获取每页大小
     * @author jeo_cb
     * @date 2020/1/13
     * @return 每页大小
     **/
    Integer getPageSize();

    /**
     * 获取当前第几页
     * @author jeo_cb
     * @date 2020/1/13
     * @return 当前页码
     **/
    Integer getCurPage();
}
