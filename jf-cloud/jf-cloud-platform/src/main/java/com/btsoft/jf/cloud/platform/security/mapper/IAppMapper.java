package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.entity.AppEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 应用管理Mapper
 * @author jeo_cb
 * @date 2019/9/7
 **/
@Mapper
public interface IAppMapper extends IBaseMapper<AppEntity> {
}
