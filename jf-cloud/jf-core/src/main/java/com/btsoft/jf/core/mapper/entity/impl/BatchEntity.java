package com.btsoft.jf.core.mapper.entity.impl;

import com.btsoft.jf.core.mapper.entity.IBatchEntity;

import java.util.List;

/**
 * 批量实体
 * @author jeo_cb
 * @date 2020/1/13
 **/
public class BatchEntity<T> implements IBatchEntity<T> {

    private static final long serialVersionUID = -1209426865359090340L;
    private List<T> list;

    @Override
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
