package com.btsoft.jf.cloud.core.context.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring 上下文
 * @author jeo_cb
 * @date 2019/8/29
 **/
@Component
public class JfCloudApplicationContextAware implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		JfCloud.setContext(applicationContext);
	}

}
