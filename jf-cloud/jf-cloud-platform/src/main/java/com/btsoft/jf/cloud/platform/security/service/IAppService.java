package com.btsoft.jf.cloud.platform.security.service;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.app.*;
import com.btsoft.jf.cloud.platform.security.vo.app.AppTokenVO;
import com.btsoft.jf.cloud.platform.security.vo.app.AppUserVO;
import com.btsoft.jf.cloud.platform.security.vo.app.AppVO;

/**
 * 角色管理Service接口
 * @author jeo_cb
 * @date 2019/9/7
 **/
public interface IAppService {

    /**
     * 应用保存，存在应用id则修改，不存在则新增
     * @author jeo_cb
     * @date 2019/9/1
     * @param  dto 角色包存参数
     * @return 保存结果
     **/
    Result saveApp(AppSaveDTO dto);

    /**
     * 删除单个应用
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 删除参数
     * @return 删除结果
     **/
    Result deleteApp(BaseIdDTO dto);

    /**
     * 根据应用id获取单个应用
     * @author jeo_cb
     * @date 2019/9/5
     * @param  id 应用id
     * @return 应用信息
     **/
    CommonResult<AppVO> findApp(Long id);

    /**
     * 分页查询应用列表
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 查询参数
     * @return 分页列表
     **/
    CommonResult<PageResult<AppVO>> findAppPage(AppQueryDTO dto);

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
     * 获取应用Token
     * @author jeo_cb
     * @date 2019/10/5
     * @param  id 应用id
     * @return 应用token
     **/
    CommonResult<AppTokenVO> findAppToken(Long id);

    /**
     * 保存应用Token
     * @author jeo_cb
     * @date 2019/10/5
     * @param  dto 保存参数
     * @return 保存结果
     **/
    Result saveAppToken(AppTokenSaveDTO dto);
}
