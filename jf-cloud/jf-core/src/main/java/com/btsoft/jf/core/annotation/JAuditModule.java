package com.btsoft.jf.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 审计模块
 * @author jeo_cb
 * @date 2019/10/5
 **/
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface JAuditModule {

	String value() default "";// 审计模块
}
