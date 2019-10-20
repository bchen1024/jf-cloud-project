package com.btsoft.jf.cloud.platform.config.service;

import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.config.dto.organization.OrganizationQueryDTO;
import com.btsoft.jf.cloud.platform.config.dto.organization.OrganizationSaveDTO;
import com.btsoft.jf.cloud.platform.config.vo.organization.OrganizationVO;

import java.util.List;

/**
 * 组织架构Service接口
 * @author jeo_cb
 * @date 2019/10/18
 **/
public interface IOrganizationService {
    
    /**
     * 组织架构列表
     * @author jeo_cb
     * @date 2019/10/18
     * @param  dto 查询参数
     * @return 组织架构列表
     **/
    CommonResult<List<OrganizationVO>> findOrganizationList(OrganizationQueryDTO dto);

    /**
     * 组织架构保存
     * @author jeo_cb
     * @date 2019/10/18
     * @param  dto 保存参数
     * @return 保存结果
     **/
    Result saveOrganization(OrganizationSaveDTO dto);
}
