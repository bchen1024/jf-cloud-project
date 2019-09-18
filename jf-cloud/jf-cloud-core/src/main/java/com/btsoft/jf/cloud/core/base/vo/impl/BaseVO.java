package com.btsoft.jf.cloud.core.base.vo.impl;

import com.btsoft.jf.cloud.core.base.vo.IBaseVO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 基类VO
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class BaseVO implements IBaseVO {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
