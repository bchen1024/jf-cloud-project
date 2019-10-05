package com.btsoft.jf.cloud.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限操作
 * @author jeo_cb
 * @date 2019/10/5
 **/
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface JOperator {

	// 权限code
	public String code() default "";
	// 权限中文描述
	public String descCN();
	// 权限英文描述
	public String descEN();
	
}
