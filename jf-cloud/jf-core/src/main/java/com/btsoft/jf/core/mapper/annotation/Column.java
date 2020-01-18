package com.btsoft.jf.core.mapper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体Table注解
 * @author jeo_cb
 * @date 2020/1/15
 **/
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

	/**默认值**/
	String value() default "";

	/**列名**/
	String name() default "";

	/**别名**/
	String alias() default "";

	/**允许插入**/
	boolean allowInsert() default true;

	/**允许更新**/
	boolean allowUpdate() default true;
}
