package com.btsoft.jf.cloud.platform.security.service;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupRoleQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupRoleSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleGroupSaveDTO;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupRoleVO;

/**
 * 群组角色Service
 * @author jeo_cb
 * @date 2019/11/4 16:29
 **/
public interface IGroupRoleService {

    /**
     * 群组角色列表
     * @author jeo_cb
     * @date 2019/10/31
     * @param dto 查询参数
     * @return 群组角色列表
     */
    CommonResult<PageResult<GroupRoleVO>> findGroupRoleList(GroupRoleQueryDTO dto);

    /**
     * 添加群组角色
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 保存参数
     * @return 保存结果
     **/
    Result addGroupRole(GroupRoleSaveDTO dto);

    /**
     * 删除群组角色
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 删除参数
     * @return 删除结果
     **/
    Result deleteGroupRole(BaseIdDTO dto);

    /**
     * 添加角色群组
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 保存参数
     * @return 保存结果
     **/
    Result addRoleGroup(RoleGroupSaveDTO dto);
}
