package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.entity.RoleUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色用户关系Mapper
 * @author jeo_cb
 * @date 2019/11/5
 **/
@Mapper
public interface IRoleUserMapper extends IBaseMapper<RoleUserEntity,Long> {
}
