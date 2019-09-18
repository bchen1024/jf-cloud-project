package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色Mapper接口
 * @author jeo_cb
 * @date 2019/8/28
 **/
@Mapper
public interface IRoleMapper extends IBaseMapper<RoleEntity>  {
}
