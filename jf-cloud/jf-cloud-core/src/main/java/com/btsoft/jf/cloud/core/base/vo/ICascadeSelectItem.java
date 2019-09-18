package com.btsoft.jf.cloud.core.base.vo;

import com.btsoft.jf.cloud.core.base.vo.impl.SelectItemVO;

import java.util.List;

/**
 * 级联选项接口
 * @author jeo_cb
 * @date 2019/8/29
 **/
public interface ICascadeSelectItem extends ISelectItem {

    /**
     * 获取子项集合
     * @author jeo_cb
     * @date 2019/8/29
     * @return 子项集合
     **/
    List<SelectItemVO> getChildren();
}
