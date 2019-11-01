package com.btsoft.jf.cloud.core.base.vo;

import java.util.List;

/**
 * 树形结构VO
 * @author jeo_cb
 * @date 2019/10/7
 **/
public interface ITreeVO<T> {

    /**
     * 值
     * @author jeo_cb
     * @date 2019/11/1
     * @return 值
     **/
    Object getValue();

    /**
     * 标题
     * @author jeo_cb
     * @date 2019/10/7
     * @return 标题
     **/
    String getTitle();

    /**
     * 是否展开
     * @author jeo_cb
     * @date 2019/10/7
     * @return 标题
     **/
    Boolean getExpand();

    /**
     * 是否禁用
     * @author jeo_cb
     * @date 2019/10/7
     * @return 是否禁用
     **/
    Boolean getDisabled();

    /**
     * 禁掉 checkbox
     * @author jeo_cb
     * @date 2019/10/7
     * @return 禁掉 checkbox
     **/
    Boolean getDisableCheckbox();

    /**
     * 是否选中
     * @author jeo_cb
     * @date 2019/10/7
     * @return 是否选中
     **/
    Boolean getSelected();

    /**
     * 是否勾选
     * @author jeo_cb
     * @date 2019/10/7
     * @return 是否勾选
     **/
    Boolean getChecked();

    /**
     * 子节点属性数组
     * @author jeo_cb
     * @date 2019/10/7
     * @return 子节点属性数组
     **/
    List<T> getChildren();
}
