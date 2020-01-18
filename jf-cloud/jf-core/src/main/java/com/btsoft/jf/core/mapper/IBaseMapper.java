package com.btsoft.jf.core.mapper;

import com.btsoft.jf.core.mapper.entity.impl.BaseEntity;
import com.btsoft.jf.core.mapper.impl.BaseMapperImpl;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Mapper基类接口
 * @author jeo_cb
 * @date 2020/1/12
 **/
public interface IBaseMapper<T extends BaseEntity<ID>,ID extends Serializable> {

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
    @SelectProvider(type = BaseMapperImpl.class,method = "getById")
    T getById(ID id);

    /**
     * 根据主键id集合查询列表
     * @author jeo_cb
     * @date 2020/1/12
     * @param  list 主键id集合
     * @return 列表集合
     **/
    @SelectProvider(type = BaseMapperImpl.class,method = "getListByIds")
    List<T> getListByIds(Collection<ID> list);

    /**
     * 插入单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @param  entity 实体对象
     * @return 受影响行数
     **/
    int insert(T entity);

    /**
     * 替换插入单个对象
     * @author jeo_cb
     * @date 2020/1/17
     * @param  entity 实体对象
     * @return 受影响行数
     **/
    int replaceInsert(T entity);

    /**
     * 忽略重复插入单个对象
     * @author jeo_cb
     * @date 2020/1/17
     * @param  entity 实体对象
     * @return 受影响行数
     **/
    int ignoreInsert(T entity);

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
    @DeleteProvider(type = BaseMapperImpl.class,method = "deleteById")
    int deleteById(ID id);

    /**
     * 根据id批量删除单个对象
     * @author jeo_cb
     * @date 2020/1/17
     * @param  list 主键id集合
     * @return 受影响行数
     */
    @DeleteProvider(type = BaseMapperImpl.class,method = "deleteByIds")
    int deleteByIds(Collection<ID> list);

    /**
     * 总数
     * @author jeo_cb
     * @date 2020/1/17
     * @param  entity 实体对象
     * @return 总数
     **/
    int count(T entity);

    /**
     * 重复校验
     * @author jeo_cb
     * @date 2020/1/17
     * @param  entity 实体对象
     * @return 重复次数
     **/
    int same(T entity);
}
