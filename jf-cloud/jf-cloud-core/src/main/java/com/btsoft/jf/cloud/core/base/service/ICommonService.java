package com.btsoft.jf.cloud.core.base.service;

import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;

import java.util.List;
import java.util.Map;

/**
 * 通用Service接口
 * @author jeo_cb
 * @date 2019/10/5
 **/
public interface ICommonService {

    /**
     * 应用权限列表
     * @author jeo_cb
     * @date 2019/10/5
     * @return 权限列表
     **/
    CommonResult<List<Map<String,String>>> listPermission();

    /**
     * 记录审计日志
     * @author jeo_cb
     * @date 2019/10/12
     * @param  log 审计信息
     * @return void
     **/
    void auditLog(Map<String, Object> log);
}
