package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.entity.RoleUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色用户关系Mapper
 * @author jeo_cb
 * @date 2019/11/5
 **/
@Mapper
public interface IRoleUserMapper extends IBaseMapper<RoleUserEntity,Long> {

    /**
     * 查询角色用户列表
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 查询参数
     * @return 角色用户列表
     **/
    List<RoleUserEntity> findRoleUserList(@Param("param") RoleUserQueryDTO dto);

    /**
     * 根据角色id删除
     * @author jeo_cb
     * @date 2019/11/4
     * @param roleId 角色id
     * @return 受影响行数
     */
    int deleteByRoleId(@Param("roleId") Long roleId);
}
