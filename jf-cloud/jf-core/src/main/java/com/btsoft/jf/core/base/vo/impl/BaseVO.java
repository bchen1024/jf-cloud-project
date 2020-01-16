package com.btsoft.jf.core.base.vo.impl;

import com.btsoft.jf.core.base.vo.IBaseVO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 基类VO
 * @author jeo_cb
 * @date 2020/1/13
 **/
public class BaseVO implements IBaseVO {
    private static final long serialVersionUID = -6380680332063216799L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
