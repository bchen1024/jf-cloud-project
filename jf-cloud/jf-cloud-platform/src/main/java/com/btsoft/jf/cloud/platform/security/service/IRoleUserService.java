package com.btsoft.jf.cloud.platform.security.service;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleUserSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.user.UserRoleSaveDTO;
import com.btsoft.jf.cloud.platform.security.vo.role.RoleUserVO;

/**
 * 角色用户Service接口
 * @author jeo_cb
 * @date 2019/11/5
 **/
public interface IRoleUserService {

    /**
     * 查询角色用户列表
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 查询参数
     * @return 角色用户列表
     **/
    CommonResult<PageResult<RoleUserVO>> findRoleUserList(RoleUserQueryDTO dto);

    /**
     * 添加用户
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 保存参数
     * @return 保存结果
     **/
    Result addRoleUser(RoleUserSaveDTO dto);

    /**
     * 删除角色用户
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 删除参数
     * @return 删除结果
     **/
    Result deleteRoleUser(BaseIdDTO dto);

    /**
     * 添加用户角色
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 保存参数
     * @return 保存结果
     **/
    Result addUserRole(UserRoleSaveDTO dto);
}
