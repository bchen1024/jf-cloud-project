package com.btsoft.jf.cloud.platform.security.service;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdAppDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleSaveDTO;
import com.btsoft.jf.cloud.platform.security.vo.role.RoleVO;

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
    Result deleteRole(BaseIdAppDTO dto);

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
}
