package com.btsoft.jf.core.mapper.annotation;

import com.btsoft.jf.core.mapper.enums.IdStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 主键注解
 * @author jeo_cb
 * @date 2020/1/15
 **/
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
    /**ID生成策略，默认数据库自增长*/
    IdStrategy idStrategy() default IdStrategy.AutoIncrement;
}
