package com.btsoft.jf.cloud.core.context.impl;

import com.btsoft.jf.cloud.core.constant.EnvironmentConstants;
import com.btsoft.jf.cloud.core.constant.ErrorCodeConstants;
import com.btsoft.jf.cloud.core.enums.impl.EnvironmentEnum;
import com.btsoft.jf.cloud.core.exception.CommonException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

/**
 * JF Cloud管理
 * @author jeo_cb
 * @date 2019/8/29
 **/
public final class JfCloud {

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

	/**
	 * 检查环境配置合法性
	 * @author jeo_cb
	 * @date 2019/9/1
	 **/
	public static boolean checkEnvironment(){
		String[] profiles=getProfiles();
		if(!isPro() && !isDev() && !isTest()){
			throw new CommonException(ErrorCodeConstants.Global.PROFILE_IS_ILLEGAL,
					String.format("[%s]配置必须包含dev、test、pro其中任意一项",
							EnvironmentConstants.Spring.PROPERTY_KEY_PROFILES));
		}
		if(isPro()){
			if(isTest() || isDev()){
				throw new CommonException(ErrorCodeConstants.Global.PROFILE_IS_WRONG,
						String.format("[%s]配置包含pro则不能配置成dev或者test",
								EnvironmentConstants.Spring.PROPERTY_KEY_PROFILES));
			}
		}
		if(isTest() && isDev()){
			throw new CommonException(ErrorCodeConstants.Global.PROFILE_IS_WRONG,
					String.format("[%s]配置包含test则不能配置成dev",
							EnvironmentConstants.Spring.PROPERTY_KEY_PROFILES));

		}
		return true;
	}

	/**
	 * 判断当前是否是传入的环境
	 * @author jeo_cb
	 * @date 2019/9/1
	 * @param  env 当前环境
	 * @return 是否
	 **/
	public static boolean isEnvironment(EnvironmentEnum env){
		String[] profiles=getProfiles();
		if(ArrayUtils.contains(profiles,env.getKey())){
			return true;
		}
		return false;
	}

	/**
	 * 是否是开发环境
	 * @author jeo_cb
	 * @date 2019/8/29
	 **/
	public static boolean isDev(){
		return isEnvironment(EnvironmentEnum.Dev);
	}

	/**
	 * 是否是测试环境
	 * @author jeo_cb
	 * @date 2019/8/29
	 **/
	public static boolean isTest(){
		return isEnvironment(EnvironmentEnum.Test);
	}

	/**
	 * 是否是生产环境
	 * @author jeo_cb
	 * @date 2019/8/29
	 **/
	public static boolean isPro(){
		return isEnvironment(EnvironmentEnum.Pro);
	}
	
	/**
	 * 获取当前应用
	 * @author jeo_cb
	 * @date 2019/9/4
	 * @return 当前应用
	 **/
	public static String getCurrentAppCode(){
		String appCode=getEnvironment().getProperty(EnvironmentConstants.Jf.JF_CLOUD_APPCODE);
		if(StringUtils.isEmpty(appCode)){
			appCode=getEnvironment().getProperty(EnvironmentConstants.Spring.SPRING_APPLICATION_NAME);
		}
		return appCode;
	}

}
