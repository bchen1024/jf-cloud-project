package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.entity.UserEntity;
import com.btsoft.jf.cloud.platform.security.vo.user.UserBaseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 根据用户id获取用户基本信息
     * @author jeo_cb
     * @date 2019/10/7
     * @param  userIds 用户id集合
     * @return 用户基本信息集合
     **/
    List<UserBaseVO> findUserListByIds(@Param("userIds") List<Long> userIds);

    /**
     * 更新用户基本信息
     * @author jeo_cb
     * @date 2019/10/7
     * @param  entity 基本信息入参
     * @return 受影响行数
     **/
    int updateUserBaseInfo(@Param("vo") UserEntity entity);

    /**
     * 更新用户状态
     * @author jeo_cb
     * @date 2019/10/7
     * @param  entity 状态更新参数
     * @return 受影响行数
     **/
    int updateUserStatus(@Param("vo") UserEntity entity);
}
