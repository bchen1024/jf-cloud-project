package com.btsoft.jf.cloud.core.base.entity.impl;

import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 *  批量操作实体类
 * @author chenbin
 * @date 2018-11-29 22:42
 */
public class BatchEntity<T> implements Serializable {

	private static final long serialVersionUID = -82286152892344133L;
	
	private List<T> list;

	private String appCode;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public Long getCurrentUserId() {
		return JfCloud.getCurrent().getCurrentUserId();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
