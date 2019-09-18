package com.btsoft.jf.cloud.platform.security.service;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdAppDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.app.AppQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppSaveDTO;
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
    Result deleteApp(BaseIdAppDTO dto);

    /**
     * 根据应用id获取单个应用
     * @author jeo_cb
     * @date 2019/9/5
     * @param  id 应用id
     * @return 应用信息
     **/
    CommonResult<AppVO> findApp(Long id);

    /**
     * 分页查询角色列表
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 查询参数
     * @return 角色分页列表
     **/
    CommonResult<PageResult<AppVO>> findAppPage(AppQueryDTO dto);
}
