package com.btsoft.jf.core.mapper.enums;

/**
 * 主键id生成策略枚举
 * @author jeo_cb
 * @date 2020/1/18
 **/
public enum IdStrategy {
    /**无生成策略*/
    None,
    /**自增长*/
    AutoIncrement,
    /**UUID*/
    UUID,
    /**时间戳*/
    TimeStamp,
    /**雪花算法*/
    Snowflake
}
