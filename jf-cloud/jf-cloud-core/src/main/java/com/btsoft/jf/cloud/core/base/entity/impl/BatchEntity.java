package com.btsoft.jf.cloud.core.base.entity.impl;

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

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
