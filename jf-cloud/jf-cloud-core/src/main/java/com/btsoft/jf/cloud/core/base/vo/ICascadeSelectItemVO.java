package com.btsoft.jf.cloud.core.base.vo;

import java.util.List;

/**
 * 级联选项接口
 * @author jeo_cb
 * @date 2019/8/29
 **/
public interface ICascadeSelectItemVO extends ISelectItemVO {

    /**
     * 获取子项集合
     * @author jeo_cb
     * @date 2019/8/29
     * @return 子项集合
     **/
    List<ISelectItemVO> getChildren();
}
