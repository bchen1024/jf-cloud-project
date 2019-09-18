package com.btsoft.jf.cloud.core.base.mapper;

import com.btsoft.jf.cloud.core.base.entity.impl.BatchEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 批量操作Mapper接口
 * @author chenbin
 * @date 2018-12-23 19:16
 */
public interface IBaseBatchMapper<T> {
	
	/**
	 * 删除多个对象
	 * @param t 删除对象列表
	 * @return 返回受影响的行数
	 */
	int deleteMultiple(@Param("vo") BatchEntity<T> t);

	/**
	 * 批量插入对象
	 * @param t 插入的对象
	 * @return 返回受影响的行数
	 */
	int createMultiple(@Param("vo") BatchEntity<T> t);
	
	/**
	 * 批量更新对象
	 * @param t 更新的列表
	 * @return 返回受影响的行数
	 */
	int updateMultiple(@Param("vo") BatchEntity<T> t);

}
