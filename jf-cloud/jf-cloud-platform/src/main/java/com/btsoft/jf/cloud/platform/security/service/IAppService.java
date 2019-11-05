package com.btsoft.jf.cloud.platform.security.service;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.app.AppQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppTokenSaveDTO;
import com.btsoft.jf.cloud.platform.security.vo.IAppVO;
import com.btsoft.jf.cloud.platform.security.vo.app.AppBaseVO;
import com.btsoft.jf.cloud.platform.security.vo.app.AppTokenVO;
import com.btsoft.jf.cloud.platform.security.vo.app.AppVO;

import java.util.List;

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

    /**
     * 填充应用信息
     * @author jeo_cb
     * @date 2019/10/31
     * @param list 集合数据
     */
    void fillAppInfo(List<? extends IAppVO> list);

    /**
     * 获取可选择的应用
     * @author jeo_cb
     * @date 2019/11/5
     * @return 应用列表
     **/
    CommonResult<List<AppBaseVO>> findAppList(AppQueryDTO dto);
}
