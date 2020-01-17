package com.btsoft.jf.core.mapper.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 基类实体接口
 * @author jeo_cb
 * @date 2020/1/12
 **/
public interface IBaseEntity<ID extends Serializable> extends Serializable {

    /**
     * 获取主键id
     * @author jeo_cb
     * @date 2020/1/12
     * @return 主键id
     **/
    ID getId();

    /**
     * 获取状态
     * @author jeo_cb
     * @date 2020/1/12
     * @return 状态
     **/
    Integer getStatus();

    /**
     * 获取创建人id
     * @author jeo_cb
     * @date 2020/1/12
     * @return 创建人id
     **/
    Long getCreateBy();

    /**
     * 获取创建时间
     * @author jeo_cb
     * @date 2020/1/12
     * @return 创建时间
     **/
    Date getCreateDate();

    /**
     * 获取最后更新人id
     * @author jeo_cb
     * @date 2020/1/12
     * @return 最后更新人id
     **/
    Long getLastUpdateBy();

    /**
     * 获取最后更新时间
     * @author jeo_cb
     * @date 2020/1/12
     * @return 最后更新时间
     **/
    Date getLastUpdateDate();

    /**
     * 获取备注
     * @author jeo_cb
     * @date 2020/1/12
     * @return 最后备注
     **/
    String getRemark();
}
