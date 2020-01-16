package com.btsoft.jf.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限资源
 * @author jeo_cb
 * @date 2019/10/5
 **/
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface JResource {

	String code();// 资源编码

	String descCN();// 资源中文描述

	String descEN();// 资源英文描述
	
	String appCode() default "";

	 String parentCode() default "";
	//排序
	int sort() default 0;
}
