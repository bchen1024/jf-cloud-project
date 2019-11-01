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
    List<AppUserEntity> findAppUserList(@Param("vo") AppUserQueryDTO dto);

}
