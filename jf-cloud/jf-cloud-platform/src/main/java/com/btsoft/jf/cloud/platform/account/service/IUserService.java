package com.btsoft.jf.cloud.platform.account.service;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdListDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.auth.dto.UpdatePasswordDTO;
import com.btsoft.jf.cloud.platform.account.dto.user.UserQueryDTO;
import com.btsoft.jf.cloud.platform.account.dto.user.UserSaveDTO;
import com.btsoft.jf.cloud.platform.account.dto.user.UserSelectQueryDTO;
import com.btsoft.jf.cloud.platform.account.dto.user.UserStatusUpdateDTO;
import com.btsoft.jf.cloud.platform.account.vo.user.UserBaseVO;
import com.btsoft.jf.cloud.platform.account.vo.user.UserEnvironmentVO;
import com.btsoft.jf.cloud.platform.account.vo.user.UserVO;

import java.util.List;
import java.util.Map;

/**
 * 账号Service接口
 * @author jeo_cb
 * @date 2019/9/28
 **/
public interface IUserService {

    /**
     * 账号分页查询
     * @author jeo_cb
     * @date 2019/9/28
     * @param  dto 查询参数
     * @return 账号分页结果
     **/
    CommonResult<PageResult<UserVO>> findUserPage(UserQueryDTO dto);

    /**
     * 创建用户
     * @author jeo_cb
     * @date 2019/9/28
     * @param  dto 创建参数
     * @return 创建结果
     **/
    Result saveUser(UserSaveDTO dto);

    /**
     * 根据id获取单个对象
     * @author jeo_cb
     * @date 2019/9/5
     * @param  id 主键id
     * @return 用户信息
     **/
    CommonResult<UserVO> findUser(Long id);

    /**
     * 根据用户id查询用户基本信息
     * @author jeo_cb
     * @date 2019/10/4
     * @param  dto 用户id集合
     * @return 用户基本信息集合
     **/
    CommonResult<List<UserBaseVO>> findUserBaseInfoList(BaseIdListDTO dto);

    /**
     * 根据用户id查询用户基本信息
     * @author jeo_cb
     * @date 2019/10/4
     * @param  dto 用户id集合
     * @return 用户基本信息Map
     **/
    CommonResult<Map<Long,UserBaseVO>> findUserBaseInfoMap(BaseIdListDTO dto);

    /**
     * 获取用当前登录信息
     * @author jeo_cb
     * @date 2019/10/4
     * @return 获取用户当前信息
     **/
    CommonResult<UserEnvironmentVO> findUserEnvironment();

    /**
     * 更新用户状态
     * @author jeo_cb
     * @date 2019/10/7
     * @param  dto 状态更新参数
     * @return 更新结果
     **/
    Result updateUserStatus(UserStatusUpdateDTO dto);

    /**
     * 更新用户密码
     * @author jeo_cb
     * @date 2019/10/28
     * @param  dto 密码更新参数
     * @return 更新结果
     **/
    Result updatePassword(UpdatePasswordDTO dto);

    /**
     * 用户选择
     * @author jeo_cb
     * @date 2019/10/4
     * @return 用户列表
     **/
    CommonResult<List<UserBaseVO>> findSelectUserList(UserSelectQueryDTO dto);

    /**
     * 清除用户当前信息缓存
     * @author jeo_cb
     * @date 2019/11/4
     * @param  userId 用户id
     * @return 清除结果
     **/
    Result clearUserEnvCache(Long userId);
}
