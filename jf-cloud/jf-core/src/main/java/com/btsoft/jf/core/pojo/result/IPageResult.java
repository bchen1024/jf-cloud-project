package com.btsoft.jf.core.pojo.result;

import com.btsoft.jf.core.pojo.page.impl.PageVO;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果
 * @author jeo_cb
 * @date 2020/1/13
 **/
public interface IPageResult<T> extends Serializable {

    /**
     * 获取列表数据
     * @author jeo_cb
     * @date 2020/1/13
     * @return 列表数据
     **/
    List<T> getList();

    /**
     * 获取分页对象
     * @author jeo_cb
     * @date 2020/1/13
     * @return 分页对象
     **/
    PageVO getPage();
}
