package com.btsoft.jf.cloud.platform.security.service;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.role.RolePermissionSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleSaveDTO;
import com.btsoft.jf.cloud.platform.security.vo.IRoleVO;
import com.btsoft.jf.cloud.platform.security.vo.role.RoleBaseVO;
import com.btsoft.jf.cloud.platform.security.vo.role.RoleVO;

import java.util.List;

/**
 * 角色Service接口
 * @author jeo_cb
 * @date 2019/8/28
 **/
public interface IRoleService{

    /**
     * 角色保存，存在角色id则修改，不存在则新增
     * @author jeo_cb
     * @date 2019/9/1
     * @param  dto 角色包存参数
     * @return 保存结果
     **/
    Result saveRole(RoleSaveDTO dto);

    /**
     * 删除单个角色
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 删除参数
     * @return 删除结果
     **/
    Result deleteRole(BaseIdDTO dto);

    /**
     * 根据角色id获取单个角色
     * @author jeo_cb
     * @date 2019/9/5
     * @param  id 角色
     * @return 角色信息
     **/
    CommonResult<RoleVO> findRole(Long id);

    /**
     * 分页查询角色列表
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 查询参数
     * @return 角色分页列表
     **/
    CommonResult<PageResult<RoleVO>> findRolePage(RoleQueryDTO dto);

    /**
     * 查询角色列表
     * @author jeo_cb
     * @date 2019/10/6
     * @param  dto 查询参数
     * @return 角色列表
     **/
    CommonResult<List<RoleBaseVO>> findRoleList(RoleQueryDTO dto);

    /**
     * 配置角色权限
     * @author jeo_cb
     * @date 2019/10/10
     * @param dto 配置参数
     * @return 配置结果
     */
    Result saveRolePermission(RolePermissionSaveDTO dto);

    /**
     * 填充角色信息
     * @author jeo_cb
     * @date 2019/10/31
     * @param list 集合数据
     */
    void fillRoleInfo(List<? extends IRoleVO> list);
}
