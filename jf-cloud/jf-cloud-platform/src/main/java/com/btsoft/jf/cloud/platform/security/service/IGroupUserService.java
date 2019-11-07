package com.btsoft.jf.cloud.platform.security.service;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupUserSaveDTO;
import com.btsoft.jf.cloud.platform.account.dto.user.UserGroupSaveDTO;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupUserVO;

/**
 * 群组用户Service接口
 * @author jeo_cb
 * @date 2019/11/4 16:33
 **/
public interface IGroupUserService {

    /**
     * 查询群组用户列表
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 查询参数
     * @return 群组用户列表
     **/
    CommonResult<PageResult<GroupUserVO>> findGroupUserList(GroupUserQueryDTO dto);

    /**
     * 添加用户
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 保存参数
     * @return 保存结果
     **/
    Result addGroupUser(GroupUserSaveDTO dto);

    /**
     * 删除群组用户
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 删除参数
     * @return 删除结果
     **/
    Result deleteGroupUser(BaseIdDTO dto);

    /**
     * 添加用户群组
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 保存参数
     * @return 保存结果
     **/
    Result addUserGroup(UserGroupSaveDTO dto);
}
