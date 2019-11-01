package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色Mapper接口
 * @author jeo_cb
 * @date 2019/8/28
 **/
@Mapper
public interface IRoleMapper extends IBaseMapper<RoleEntity,Long>  {

    /**
     * 根据角色编码获取角色
     * @author jeo_cb
     * @date 2019/11/1
     * @param appCode 应用编码
     * @param roleCode 角色编码
     * @return 角色信息
     */
    RoleEntity findRoleByCode(@Param("appCode") String appCode, @Param("roleCode")String roleCode);
}
