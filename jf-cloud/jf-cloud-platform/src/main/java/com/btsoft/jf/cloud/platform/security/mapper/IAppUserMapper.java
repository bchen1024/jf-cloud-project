package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.dto.app.AppUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.entity.AppUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 应用用户管理Mapper
 * @author jeo_cb
 * @date 2019/10/3
 **/
@Mapper
public interface IAppUserMapper extends IBaseMapper<AppUserEntity,Long> {

    /**
     * 分页查询应用用户列表
     * @author jeo_cb
     * @date 2019/10/3
     * @param  dto 查询参数
     * @return 应用用户列表
     **/
    List<AppUserEntity> findAppUserList(@Param("param") AppUserQueryDTO dto);

    /**
     * 根据应用id删除
     * @author jeo_cb
     * @date 2019/11/4
     * @param appId 应用id
     * @return 受影响行数
     */
    int deleteByAppId(@Param("appId") Long appId);

    /**
     * 根据角色id删除
     * @author jeo_cb
     * @date 2019/11/4
     * @param roleId 角色id
     * @return 受影响行数
     */
    int deleteByRoleId(@Param("roleId") Long roleId);
}
