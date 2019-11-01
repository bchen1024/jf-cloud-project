package com.btsoft.jf.cloud.platform.config.service;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
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

    /**
     * 组织架构详情
     * @author jeo_cb
     * @date 2019/11/1
     * @param  id 组织架构id
     * @return 组织架构信息
     **/
    CommonResult<OrganizationVO> findOrganization(Long id);

    /**
     * 删除组织架构
     * @author jeo_cb
     * @date 2019/11/1
     * @param  dto 删除参数
     * @return 删除结果
     **/
    Result deleteOrganization(BaseIdDTO dto);
}
