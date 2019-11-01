package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.dto.user.UserSelectQueryDTO;
import com.btsoft.jf.cloud.platform.security.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 账号mapper
 * @author jeo_cb
 * @date 2019/9/28
 **/
@Mapper
public interface IUserMapper extends IBaseMapper<UserEntity,Long> {

    /**
     * 根据中文名获取同名用户数量
     * @author jeo_cb
     * @date 2019/9/29
     * @param userCn 中文名称
     * @return 用户数量
     **/
    int findUserCountByUserCn(@Param("userCn") String userCn);

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

    /**
     * 校验用户手机号码是否重复
     * @author jeo_cb
     * @date 2019/10/23
     * @param entity 用户信息
     * @return 数量
     */
    int validUserMobile(@Param("vo") UserEntity entity);

    /**
     * 校验用户邮箱是否重复
     * @author jeo_cb
     * @date 2019/10/23
     * @param entity 用户信息
     * @return 数量
     */
    int validUserEmail(@Param("vo") UserEntity entity);

    /**
     * 获取有效的登录用户
     * @author jeo_cb
     * @date 2019/10/25
     * @param userAccount 账号
     * @return 用户信息
     */
    UserEntity findValidUser(@Param("userAccount") String userAccount);

    /**
     * 更新用户密码错误次数和锁定状态
     *  @author jeo_cb
     * @date 2019/10/25
     * @param entity 用户信息
     * @return 受影响行数
     */
    int updateErrorNumAndStatus(@Param("vo") UserEntity entity);

    /**
     * 用户选择
     * @author jeo_cb
     * @date 2019/10/4
     * @return 用户列表
     **/
    List<UserEntity> findSelectUserList(@Param("vo")  UserSelectQueryDTO dto);
}
