package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.entity.UserDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户详情Mapper
 * @author jeo_cb
 * @date 2019/9/29
 **/
@Mapper
public interface IUserDetailMapper extends IBaseMapper<UserDetailEntity> {

    /**
     * 创建用户详情
     * @author jeo_cb
     * @date 2019/9/29
     * @param  entity 用户详情信息
     * @return 受影响行数
     **/
    int createSingle(@Param("vo") UserDetailEntity entity);
}
