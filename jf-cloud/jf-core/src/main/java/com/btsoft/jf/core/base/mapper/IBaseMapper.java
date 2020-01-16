package com.btsoft.jf.core.base.mapper;

import com.btsoft.jf.core.base.entity.impl.BaseEntity;
import com.btsoft.jf.core.base.mapper.impl.BaseMapperImpl;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Collection;
import java.util.List;

/**
 * Mapper基类接口
 * @author jeo_cb
 * @date 2020/1/12
 **/
public interface IBaseMapper<T extends BaseEntity,ID> {

    /**
     * 列表查询
     * @author jeo_cb
     * @date 2020/1/12
     * @param  entity 查询参数
     * @return 实体列表
     **/
    @SelectProvider(type = BaseMapperImpl.class,method = "getList")
    List<T> getList(T entity);

    /**
     * 根据id获取单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @param  id 主键id
     * @return 实体对象
     **/
    T getById(ID id);

    /**
     * 根据主键id集合查询列表
     * @author jeo_cb
     * @date 2020/1/12
     * @param  ids 主键id集合
     * @return 列表集合
     **/
    List<T> getListByIds(Collection<ID> ids);

    /**
     * 插入单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @param  entity 实体对象
     * @return 受影响行数
     **/
    int insert(T entity);

    /**
     * 更新单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @param  entity 实体对象
     * @return 受影响行数
     **/
    int update(T entity);

    /**
     * 更新部分字段
     * @author jeo_cb
     * @date 2020/1/13
     * @param  entity 实体对象
     * @return 受影响行数
     **/
    int updatePatch(T entity);

    /**
     * 根据id删除单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @param  id 主键id
     * @return 受影响行数
     **/
    @DeleteProvider
    int deleteById(ID id);
}
