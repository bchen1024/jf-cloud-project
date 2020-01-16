package com.btsoft.jf.cloud.generate.mapper;

import com.btsoft.jf.cloud.generate.entity.DatabaseEntity;
import com.btsoft.jf.core.base.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据库Mapper接口
 * @author jeo_cb
 * @date 2020/1/12
 **/
@Mapper
public interface IDatabaseMapper extends IBaseMapper<DatabaseEntity,Long> {
}
