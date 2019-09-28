package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 账号mapper
 * @author jeo_cb
 * @date 2019/9/28
 **/
@Mapper
public interface IUserMapper extends IBaseMapper<UserEntity> {

    /**
     * 根据中文名获取同名用户数量
     * @author jeo_cb
     * @date 2019/9/29
     * @param userCn 中文名称
     * @return 用户数量
     **/
    int findUserCountByUserCn(@Param("userCn") String userCn);
}
