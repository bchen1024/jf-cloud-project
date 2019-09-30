package com.btsoft.jf.cloud.platform.security.service;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdListDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.user.UserCreateDTO;
import com.btsoft.jf.cloud.platform.security.dto.user.UserQueryDTO;
import com.btsoft.jf.cloud.platform.security.vo.user.UserBaseVO;
import com.btsoft.jf.cloud.platform.security.vo.user.UserVO;

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
    Result createUser(UserCreateDTO dto);


    CommonResult<List<UserBaseVO>> findUserBaseInfoList(BaseIdListDTO dto);

    CommonResult<Map<Long,UserBaseVO>> findUserBaseInfoMap(BaseIdListDTO dto);
}
