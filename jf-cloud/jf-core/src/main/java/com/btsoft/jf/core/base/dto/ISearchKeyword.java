package com.btsoft.jf.core.base.dto;

/**
 * 关键字查询接口
 * @author jeo_cb
 * @date 2020/1/13
 **/
public interface ISearchKeyword extends IBaseDTO {

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
