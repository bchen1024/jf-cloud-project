package com.btsoft.jf.cloud.core.base.mapper;

import com.btsoft.jf.cloud.core.base.entity.impl.BaseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
/**
 * 
 * @desc 基类Mapper对象
 * @author chenbin
 * @date 2018-10-14 11:08:23
 */
public interface IBaseMapper<T extends BaseEntity,ID> extends IBaseBatchMapper<T>{

	/**
	 * 插入单个对象
	 * @param t 插入对象
	 * @return 返回受影响行数
	 */
	int createSingle(@Param("param") T t);

	/**
	 * 查询单个对象
	 * @param t 查询条件对象
	 * @return 返回单个对象
	 */
	T findSingle(@Param("param") T t);

	/**
	 * 根据id查询单个对象
	 * @param id 主键id
	 * @return 返回单个对象
	 */
	T findSingleById(@Param("id")ID id);

	/**
	 * 更新单个对象
	 * @param t 更新对象
	 * @return 返回受影响行数
	 */
	int updateSingle(@Param("param") T t);

	/**
	 * 更新对象某个字段
	 * @param t 更新对象
	 * @return 返回受影响行数
	 */
	int patchSingle(@Param("param") T t);

	/**
	 * 删除单个对象
	 * @param t 删除对象
	 * @return 返回受影响行数
	 */
	int deleteSingle(@Param("param") T t);

	/**
	 * 根据id删除单个对象
	 * @param id 主键id
	 * @return 返回受影响行数
	 */
	int deleteSingleById(@Param("id") ID id);
	
	/**
	 * 查询对象列表
	 * @param t 查询参数对象
	 * @return 返回对象列表
	 */
	List<T> findList(@Param("param") T t);

	/**
	 * 根据id获取列表
	 * @author jeo_cb
	 * @date 2019/10/3
	 * @param  ids 主键id集合
	 * @return 对象列表
	 **/
	List<T> findListByIds(Collection<ID> ids);

}
