package com.btsoft.jf.cloud.core.base.dto;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseSortDTO;

import java.io.Serializable;
import java.util.List;

/**
 * 排序接口
 * @author jeo_cb
 * @date 2019/8/29
 **/
public interface ISort extends Serializable {

    /**
     * 获取排序集合
     * @author jeo_cb
     * @date 2019/8/29
     * @return 排序集合
     **/
    List<BaseSortDTO> getSorts();
}
