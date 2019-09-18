package com.btsoft.jf.cloud.core.base.result;

import com.btsoft.jf.cloud.core.base.dto.IBasePage;

import java.util.List;

/**
 * 分页结果接口
 * @author jeo_cb
 * @date 2019/8/29
 **/
public interface IPageResult<T> extends IBasePage {
    /**
     * 获取总数
     * @author jeo_cb
     * @date 2019/8/29
     * @return 总数
     **/
    Long getTotal();

    /**
     * 获取总页数
     * @author jeo_cb
     * @date 2019/8/29
     * @return 总页数
     **/
    Integer getTotalPage();

    /**
     * 获取数据集合
     * @author jeo_cb
     * @date 2019/8/29
     * @return 数据集合
     **/
    List<T> getList();
}
