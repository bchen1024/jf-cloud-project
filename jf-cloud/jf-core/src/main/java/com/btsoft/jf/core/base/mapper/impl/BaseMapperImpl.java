package com.btsoft.jf.core.base.mapper.impl;

import com.btsoft.jf.core.base.entity.impl.BaseEntity;

import java.util.Collection;

/**
 * 通用mapper实现类
 * @author jeo_cb
 * @date 2020/1/14
 **/
public class BaseMapperImpl<T extends BaseEntity<ID>, ID>{
    public String getList(T entity) {
        return "select * from db_database_t";
    }

    public String getById(ID id) {
        return null;
    }

    public String getListByIds(Collection<ID> ids) {
        return null;
    }

    public String insert(T entity) {
        return null;
    }

    public String update(T entity) {
        return null;
    }

    public String updatePatch(T entity) {
        return null;
    }

    public String deleteById(ID id) {
        return null;
    }
}
