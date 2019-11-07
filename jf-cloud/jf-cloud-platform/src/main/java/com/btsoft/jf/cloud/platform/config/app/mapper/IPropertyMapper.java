package com.btsoft.jf.cloud.platform.config.app.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.config.app.entity.PropertyEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典Mapper接口
 * @author jeo_cb
 * @date 2019/11/7
 **/
@Mapper
public interface IPropertyMapper extends IBaseMapper<PropertyEntity,Long> {
}
