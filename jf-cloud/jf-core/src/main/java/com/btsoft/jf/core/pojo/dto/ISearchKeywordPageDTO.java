package com.btsoft.jf.core.pojo.dto;

import com.btsoft.jf.core.pojo.page.IPage;

/**
 * 关键字分页查询DTO
 * @author jeo_cb
 * @date 2020/1/13
 **/
public interface ISearchKeywordPageDTO extends IPage {
    /**
     * 获取查询类型
     * @author jeo_cb
     * @date 2020/1/13
     * @return 关键字类型
     **/
    String getKeywordField();

    /**
     * 获取关键字
     * @author jeo_cb
     * @date 2020/1/13
     * @return 关键字
     **/
    Object getKeywordValue();
}
