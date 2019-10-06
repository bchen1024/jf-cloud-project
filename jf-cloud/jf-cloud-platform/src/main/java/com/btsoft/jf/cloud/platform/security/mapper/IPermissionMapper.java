package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.entity.PermissionEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限Mapper接口
 * @author jeo_cb
 * @date 2019/10/6
 **/
@Mapper
public interface IPermissionMapper  extends IBaseMapper<PermissionEntity> {
}
