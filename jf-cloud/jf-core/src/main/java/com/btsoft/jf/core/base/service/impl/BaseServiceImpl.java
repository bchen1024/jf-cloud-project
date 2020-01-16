package com.btsoft.jf.core.base.service.impl;

import com.btsoft.jf.core.base.dto.ISearchKeywordPageDTO;
import com.btsoft.jf.core.base.entity.impl.BaseEntity;
import com.btsoft.jf.core.base.mapper.IBaseMapper;
import com.btsoft.jf.core.base.result.impl.CommonResult;
import com.btsoft.jf.core.base.result.impl.PageResult;
import com.btsoft.jf.core.base.service.IBaseService;
import com.btsoft.jf.core.base.vo.impl.BaseVO;
import com.btsoft.jf.core.util.BeanUtils;
import com.btsoft.jf.core.util.CommonResultUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基类Service实现<br>
 *     泛型V：返回值
 *     泛型D：入参
 *     泛型T：数据库Entity实体
 *     泛型ID：主键id
 * @author jeo_cb
 * @date 2020/1/13
 **/
public class BaseServiceImpl<V extends BaseVO,T extends BaseEntity<ID>,ID> implements IBaseService<V,ID> {

    @Autowired
    private IBaseMapper<T,ID> mapper;

    /**
     * 获取分页列表
     * @author jeo_cb
     * @date 2020/1/13
     * @param  dto 查询参数
     * @return 分页结果
     **/
    @Override
    public CommonResult<PageResult<V>> getPageList(ISearchKeywordPageDTO dto) {
        //获取Entity实体Class
        Class<T> clazz = (Class<T>)getClazz(1);
        //将查询DTO转换成Entity实体
        T entity= BeanUtils.dtoToEntity(clazz,dto);
        //启动分页查询
        Page page= PageHelper.startPage(dto.getCurPage(),dto.getPageSize(),true);
        //执行查询
        mapper.getList(entity);
        //获取返回VO对象class
        Class<V> resultClass = (Class<V>)getClazz(0);
        //转换分页结果返回
        return CommonResultUtils.pageResult(resultClass,page);
    }

    /**
     * 根据id获取单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @param  id 主键id
     * @return VO对象
     **/
    @Override
    public CommonResult<V> getById(ID id) {
        //根据id查询
        T entity=mapper.getById(id);
        //获取返回VO对象class
        Class<V> clazz = (Class<V>) getClazz(0);
        //转换实体entity为返回VO
        return CommonResultUtils.result(clazz,entity);
    }

    @Override
    public CommonResult<List<V>> getListByIds(Collection<ID> ids) {
        if(CollectionUtils.isEmpty(ids)){
            return CommonResultUtils.emptyList();
        }
        //id去重
        ids=ids.stream().distinct().collect(Collectors.toList());
        //根据id集合批量查询
        List<T> list=mapper.getListByIds(ids);
        Class<V> clazz = (Class<V>) getClazz(0);
        //转换实体entity为返回VO
        return CommonResultUtils.resultList(clazz,list);
    }

    /**
     * 插入单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @param  dto 实体对象
     * @return 主键id
     **/
    @Override
    public <D> CommonResult<ID> insert(D dto) {
        //获取Entity实体Class
        Class<T> clazz = (Class<T>)getClazz(1);
        //将查询DTO转换成Entity实体
        T entity= BeanUtils.dtoToEntity(clazz,dto);
        //插入数据
        int rows=mapper.insert(entity);
        //返回主键id
        return CommonResultUtils.result(rows,entity.getId());
    }

    /**
     * 更新单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @param  dto 实体对象
     * @return 受影响行数
     **/
    @Override
    public <D> CommonResult<Integer> update(D dto) {
        //获取Entity实体Class
        Class<T> clazz = (Class<T>)getClazz(1);
        //将查询DTO转换成Entity实体
        T entity= BeanUtils.dtoToEntity(clazz,dto);
        //更新数据
        int rows=mapper.update(entity);
        //返回受影响行数
        return CommonResultUtils.result(rows,rows);
    }

    /**
     * 更新部分字段
     * @author jeo_cb
     * @date 2020/1/13
     * @param  dto 实体对象
     * @return 受影响行数
     **/
    @Override
    public <D> CommonResult<Integer> updatePatch(D dto) {
        //获取Entity实体Class
        Class<T> clazz = (Class<T>)getClazz(1);
        //将查询DTO转换成Entity实体
        T entity= BeanUtils.dtoToEntity(clazz,dto);
        //更新数据
        int rows=mapper.updatePatch(entity);
        //返回受影响行数
        return CommonResultUtils.result(rows,rows);
    }

    /**
     * 根据id删除单个对象
     * @author jeo_cb
     * @date 2020/1/12
     * @param  id 主键id
     * @return 受影响行数
     **/
    @Override
    public CommonResult<Integer> deleteById(ID id) {
        //删除数据
        int rows=mapper.deleteById(id);
        //返回受影响行数
        return CommonResultUtils.result(rows,rows);
    }

    /**
     * 获取泛型Class
     * @author jeo_cb
     * @date 2020/1/14
     * @param  index 泛型
     * @return 泛型类型
     **/
    private Type getClazz(int index){
        ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
        return parameterizedType.getActualTypeArguments()[index];
    }
}
