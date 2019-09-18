package com.btsoft.jf.cloud.core.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 基类实体接口
 * @author jeo_cb
 * @date 2019/8/29
 **/
public interface IBaseEntity extends Serializable {

    /**
     * 获取有效标识
     * @author jeo_cb
     * @date 2019/8/29
     * @return 有效标识
     **/
    String getEnableFlag();

    /**
     * 获取有效标识
     * @author jeo_cb
     * @date 2019/8/29
     * @return 有效标识
     **/
    Long getCreateBy();

    /**
     * 获取创建时间
     * @author jeo_cb
     * @date 2019/8/29
     * @return 创建时间
     **/
    Date getCreateDate();

    /**
     * 获取最后更新人id
     * @author jeo_cb
     * @date 2019/8/29
     * @return 最后更新人id
     **/
    Long getLastUpdateBy();

    /**
     * 获取最后更新时间
     * @author jeo_cb
     * @date 2019/8/29
     * @return 最后更新时间
     **/
    Date getLastUpdateDate();

    /**
     * 获取当前登录者id
     * @author jeo_cb
     * @date 2019/9/3
     * @return 当前登录id
     **/
    Long getCurrentUserId();
}
