package com.btsoft.jf.cloud.platform.security.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdAppDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.app.AppQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppSaveDTO;
import com.btsoft.jf.cloud.platform.security.service.IAppService;
import com.btsoft.jf.cloud.platform.security.vo.app.AppVO;

/**
 * 应用Service实现类
 * @author jeo_cb
 * @date 2019/9/7
 **/
public class AppServiceImpl implements IAppService {
    @Override
    public Result saveApp(AppSaveDTO dto) {
        return null;
    }

    @Override
    public Result deleteApp(BaseIdAppDTO dto) {
        return null;
    }

    @Override
    public CommonResult<AppVO> findApp(Long id) {
        return null;
    }

    @Override
    public CommonResult<PageResult<AppVO>> findAppPage(AppQueryDTO dto) {
        return null;
    }
}
