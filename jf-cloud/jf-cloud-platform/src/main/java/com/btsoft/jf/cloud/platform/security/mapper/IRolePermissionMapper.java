package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.dto.role.RolePermissionSaveDTO;
import com.btsoft.jf.cloud.platform.security.entity.RolePermissionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author jeo_cb
 * @description 角色权限
 * @date 2019/10/10 18:39
 **/
@Mapper
public interface IRolePermissionMapper extends IBaseMapper<RolePermissionEntity> {

    /**
     * 删除已经去掉的配置
     * @author jeo_cb
     * @date 2019/10/10
     * @param dto 删除参数
     * @return 受影响行数
     */
    int deleteRolePermission(@Param("vo") RolePermissionSaveDTO dto);
}
