package com.btsoft.jf.core.context.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

/**
 * JF Cloud管理
 * @author jeo_cb
 * @date 2019/8/29
 **/
public final class JfSupport {

	/** Spring上下文*/
	private static ApplicationContext context;

	/** 请求上下文线程变量*/
	private final static ThreadLocal<RequestContext> REQUEST_CONTEXT=new ThreadLocal<>();

	/**
	 * 设置请求上下文到线程变量中
	 * @author jeo_cb
	 * @date 2019/8/29
	 * @param  current 请求上下文
	 **/
	public static void setCurrent(RequestContext current){
		REQUEST_CONTEXT.set(current);
	}

	/**
	 * 移除线程变量中的请求上下文
	 * @author jeo_cb
	 * @date 2019/8/29
	 **/
	public static void removeCurrent(){
		REQUEST_CONTEXT.remove();
	}

	/**
	 * 获取线程变量中的请求上下文
	 * @author jeo_cb
	 * @date 2019/8/29
	 **/
	public static RequestContext getCurrent(){
		return getCurrent(false);
	}

	/**
	 * 获取线程变量中的请求上下文
	 * @author jeo_cb
	 * @date 2019/8/29
	 * @param allowNull 是否允许返回空值
	 **/
	public static RequestContext getCurrent(boolean allowNull){
		RequestContext rc=REQUEST_CONTEXT.get();
		if(!allowNull){
			Assert.notNull(rc,"RequestContext is not null");
		}
		return rc;
	}

	/**
	 * 设置Spring上下文
	 * @author jeo_cb
	 * @date 2019/8/29
	 **/
	public static void setContext(ApplicationContext applicationContext){
		if(context==null){
			context=applicationContext;
		}
	}

	/**
	 * 获取Spring上下文
	 * @author jeo_cb
	 * @date 2019/8/29
	 **/
	public static ApplicationContext getContext(){
		return context;
	}

	/**
	 * 获取Spring环境对象
	 * @author jeo_cb
	 * @date 2019/8/29
	 **/
	public static Environment getEnvironment(){
		return context.getEnvironment();
	}

	/**
	 * 获取当前环境数组
	 * @author jeo_cb
	 * @date 2019/9/1
	 **/
	public static String[] getProfiles(){
		Environment environment=getEnvironment();
		String[] profiles=environment.getActiveProfiles();
		if(ArrayUtils.isEmpty(profiles)){
			profiles=environment.getDefaultProfiles();
		}
		return profiles;
	}

}
