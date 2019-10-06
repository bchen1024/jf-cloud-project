package com.btsoft.jf.cloud.platform.security.service;

import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.permission.PermissionSyncDTO;

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
}
