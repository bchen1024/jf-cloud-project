package com.btsoft.jf.cloud.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 审计日志
 * @author jeo_cb
 * @date 2019/10/5
 **/
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface JAuditLog {

	// 操作类型
	public String type() default "";

	// 操作描述
	public String message() default "";

}
