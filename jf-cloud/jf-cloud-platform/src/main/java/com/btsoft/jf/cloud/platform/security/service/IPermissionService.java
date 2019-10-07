package com.btsoft.jf.cloud.platform.security.service;

import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.permission.PermissionQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.permission.PermissionSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.permission.PermissionSyncDTO;
import com.btsoft.jf.cloud.platform.security.vo.permission.PermissionVO;

import java.util.List;

/**
 * 权限Service接口
 * @author jeo_cb
 * @date 2019/10/6
 **/
public interface IPermissionService {

    /**
     * 权限同步
     * @author jeo_cb
     * @date 2019/10/6
     * @param  dto 同步参数
     * @return 同步结果
     **/
    Result syncPermission(PermissionSyncDTO dto);

    /**
     * 获取权限树
     * @author jeo_cb
     * @date 2019/10/7
     * @param  dto 查询参数
     * @return 权限树
     **/
    CommonResult<List<PermissionVO>> findPermissionTree(PermissionQueryDTO dto);

    /**
     * 权限保存
     * @author jeo_cb
     * @date 2019/10/7
     * @param  dto
     * @return 保存结果
     **/
    Result savePermission(PermissionSaveDTO dto);
}
