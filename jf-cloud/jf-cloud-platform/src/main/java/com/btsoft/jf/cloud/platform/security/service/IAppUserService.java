package com.btsoft.jf.cloud.platform.security.service;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.app.AppUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppUserSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.user.UserAppSaveDTO;
import com.btsoft.jf.cloud.platform.security.vo.app.AppUserVO;

/**
 * 应用用户Service接口
 * @author jeo_cb
 * @date 2019/11/5
 **/
public interface IAppUserService {

    /**
     * 应用用户列表
     * @author jeo_cb
     * @date 2019/10/3
     * @param  dto 查询参数
     * @return 应用用户
     **/
    CommonResult<PageResult<AppUserVO>> findAppUserPage(AppUserQueryDTO dto);

    /**
     * 删除单个应用用户
     * @author jeo_cb
     * @date 2019/9/29
     * @param  dto 删除参数
     * @return 删除结果
     **/
    Result deleteAppUser(BaseIdDTO dto);

    /**
     * 添加应用用户
     * @author jeo_cb
     * @date 2019/10/4
     * @param  dto 保存参数
     * @return 保存结果
     **/
    Result addAppUser(AppUserSaveDTO dto);

    /**
     * 添加用户应用
     * @author jeo_cb
     * @date 2019/10/4
     * @param  dto 保存参数
     * @return 保存结果
     **/
    Result addUserApp(UserAppSaveDTO dto);
}
