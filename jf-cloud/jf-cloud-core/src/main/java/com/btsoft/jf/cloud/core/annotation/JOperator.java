package com.btsoft.jf.cloud.core.annotation;

import com.btsoft.jf.cloud.core.enums.impl.AuthTypeEnum;

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
	String code() default "";
	// 权限中文描述
	String descCN();
	// 权限英文描述
	String descEN();
	//排序
	int sort() default 0;
	//授权类型
	AuthTypeEnum authType() default AuthTypeEnum.AuthUser;
	
}
