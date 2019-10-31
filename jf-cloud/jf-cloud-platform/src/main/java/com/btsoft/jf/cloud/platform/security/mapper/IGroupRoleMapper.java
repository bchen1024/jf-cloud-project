package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupRoleQueryDTO;
import com.btsoft.jf.cloud.platform.security.entity.GroupRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 群组角色Mapper接口
 * @author jeo_cb
 * @date 2019/10/31
 **/
@Mapper
public interface IGroupRoleMapper extends IBaseMapper<GroupRoleEntity> {

    /**
     * 群组角色列表
     * @author jeo_cb
     * @date 2019/10/31
     * @param dto 查询参数
     * @return 群组角色列表
     */
    List<GroupRoleEntity> findGroupRoleList(@Param("vo") GroupRoleQueryDTO dto);
}
