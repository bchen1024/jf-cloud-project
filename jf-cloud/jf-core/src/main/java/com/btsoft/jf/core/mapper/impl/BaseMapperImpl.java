package com.btsoft.jf.core.mapper.impl;

import com.btsoft.jf.core.mapper.IBaseMapper;
import com.btsoft.jf.core.mapper.entity.impl.BaseEntity;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.springframework.core.ResolvableType;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * 通用mapper实现类
 * @author jeo_cb
 * @date 2020/1/14
 **/
public class BaseMapperImpl<T extends BaseEntity<ID>, ID extends Serializable>{

    /**
     * 列表查询
     * @author jeo_cb
     * @date 2020/1/17
     * @param  entity 查询参数
     * @return 实体列表
     **/
    public String getList(T entity) {
        return SqlProviderSupport.getSelectSql(entity.getClass(),false,false,false,false,entity);
    }

    /**
     * 根据id获取单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @return 实体对象
     **/
    public String getById(ProviderContext context) {
        return SqlProviderSupport.getSelectSql(getEntityClass(context),false,false,true,false,null);
    }

    /**
     * 根据主键id集合查询列表
     * @author jeo_cb
     * @date 2020/1/12
     * @return 列表集合
     **/
    public String getListByIds(Collection<ID> list,ProviderContext context) {
        return SqlProviderSupport.getSelectSql(getEntityClass(context),false,false,false,true,list);
    }

    /**
     * 插入单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @param  entity 实体对象
     * @return 受影响行数
     **/
    public String insert(T entity) {
        return null;
    }

    /**
     * 更新单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @param  entity 实体对象
     * @return 受影响行数
     **/
    public String update(T entity) {
        return null;
    }

    /**
     * 更新部分字段
     * @author jeo_cb
     * @date 2020/1/13
     * @param  entity 实体对象
     * @return 受影响行数
     **/
    public String updatePatch(T entity) {
        return null;
    }

    /**
     * 根据id删除单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @return 受影响行数
     **/
    public String deleteById(ProviderContext context) {
        return SqlProviderSupport.getDeleteSql(getEntityClass(context),true,false,null);
    }

    /**
     * 根据id批量删除单个对象
     * @author jeo_cb
     * @date 2020/1/17
     * @param  list 主键id集合
     * @return 受影响行数
     */
    public String deleteByIds(Collection<ID> list,ProviderContext context) {
        return SqlProviderSupport.getDeleteSql(getEntityClass(context),false,true,list);
    }

    /**
     * 总数
     * @author jeo_cb
     * @date 2020/1/17
     * @param  entity 实体对象
     * @return 总数
     **/
    public String count(T entity){
        return SqlProviderSupport.getSelectSql(entity.getClass(),false,true,false,false,entity);
    }

    public String same(T entity){
        return null;
    }

    private Class<?> getEntityClass(ProviderContext context) {
        Class<?> mapperType = context.getMapperType();
        for (Type parent : mapperType.getGenericInterfaces()) {
            ResolvableType parentType = ResolvableType.forType(parent);
            if (parentType.getRawClass() == IBaseMapper.class) {
                return parentType.getGeneric(0).getRawClass();
            }
        }
        return null;
    }
}
