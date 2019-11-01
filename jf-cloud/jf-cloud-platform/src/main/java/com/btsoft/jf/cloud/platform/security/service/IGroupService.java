package com.btsoft.jf.cloud.platform.security.service;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.group.*;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupRoleVO;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupUserVO;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupVO;

/**
 * 群组Service接口
 * @author jeo_cb
 * @date 2019/9/30
 **/
public interface IGroupService {

    /**
     * 分页查询群组列表
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 查询参数
     * @return 群组分页列表
     **/
    CommonResult<PageResult<GroupVO>> findGroupPage(GroupQueryDTO dto);

    /**
     * 根据群组id获取单个群组
     * @author jeo_cb
     * @date 2019/9/5
     * @param  id 群组
     * @return 群组信息
     **/
    CommonResult<GroupVO> findGroup(Long id);

    /**
     * 群组保存，存在群组id则修改，不存在则新增
     * @author jeo_cb
     * @date 2019/9/1
     * @param  dto 群组包存参数
     * @return 保存结果
     **/
    Result saveGroup(GroupSaveDTO dto);

    /**
     * 删除单个群组
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 删除参数
     * @return 删除结果
     **/
    Result deleteGroup(BaseIdDTO dto);

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

}
