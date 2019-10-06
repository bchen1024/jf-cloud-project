package com.btsoft.jf.cloud.core.base.vo;

import java.io.Serializable;

/**
 * 选项接口
 * @author jeo_cb
 * @date 2019/8/29
 **/
public interface ISelectItem extends Serializable {

    /**
     * 获取Value值
     * @author jeo_cb
     * @date 2019/8/29
     * @return Value值
     **/
    Object getValue();

    /**
     * 获取Text显示值
     * @author jeo_cb
     * @date 2019/8/29
     * @return Text显示值
     **/
    Object getLabel();

    /**
     * 获取是否禁用
     * @author jeo_cb
     * @date 2019/8/29
     * @return 是否禁用
     **/
    Boolean getDisabled();

    /**
     * 获取是否选中
     * @author jeo_cb
     * @date 2019/8/29
     * @return 是否选中
     **/
    Boolean getSelected();
}
