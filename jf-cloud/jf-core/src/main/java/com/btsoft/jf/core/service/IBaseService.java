package com.btsoft.jf.core.service;

import com.btsoft.jf.core.pojo.dto.ISearchKeywordPageDTO;
import com.btsoft.jf.core.pojo.result.impl.CommonResult;
import com.btsoft.jf.core.pojo.result.impl.PageResult;
import com.btsoft.jf.core.pojo.vo.impl.BaseVO;

import java.util.Collection;
import java.util.List;

/**
 * 基类Service
 * @author jeo_cb
 * @date 2020/1/13
 **/
public interface IBaseService<V extends BaseVO,ID>{

    /**
     * 获取分页列表
     * @author jeo_cb
     * @date 2020/1/13
     * @param  dto 查询参数
     * @return 分页结果
     **/
    CommonResult<PageResult<V>> getPageList(ISearchKeywordPageDTO dto);

    /**
     * 根据id获取单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @param  id 主键id
     * @return VO对象
     **/
    CommonResult<V> getById(ID id);

    /**
     * 根据主键id集合查询列表
     * @author jeo_cb
     * @date 2020/1/12
     * @param  ids 主键id集合
     * @return 列表集合
     **/
    CommonResult<List<V>> getListByIds(Collection<ID> ids);

    /**
     * 插入单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @param  dto 实体对象
     * @return 主键id
     **/
    <D> CommonResult<ID> insert(D dto);

    /**
     * 更新单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @param  dto 实体对象
     * @return 受影响行数
     **/
    <D> CommonResult<Integer> update(D dto);

    /**
     * 更新部分字段
     * @author jeo_cb
     * @date 2020/1/13
     * @param  dto 实体对象
     * @return 受影响行数
     **/
    <D> CommonResult<Integer> updatePatch(D dto);

    /**
     * 根据id删除单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @param  id 主键id
     * @return 受影响行数
     **/
    CommonResult<Integer> deleteById(ID id);

    /**
     * 根据id集合删除对象
     * @author jeo_cb
     * @date 2020/1/12
     * @param  ids 主键id
     * @return 受影响行数
     **/
    CommonResult<Integer> deleteByIds(Collection<ID> ids);
}
