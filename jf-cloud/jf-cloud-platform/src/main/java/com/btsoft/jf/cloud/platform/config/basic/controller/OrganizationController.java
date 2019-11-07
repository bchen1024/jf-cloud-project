package com.btsoft.jf.cloud.platform.config.basic.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.platform.config.basic.dto.organization.OrganizationQueryDTO;
import com.btsoft.jf.cloud.platform.config.basic.dto.organization.OrganizationSaveDTO;
import com.btsoft.jf.cloud.platform.config.basic.service.IOrganizationService;
import com.btsoft.jf.cloud.platform.config.basic.vo.organization.OrganizationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author jeo_cb
 * @date 2019/10/18
 **/
@RestController
@Api(tags = "组织架构")
@RequestMapping("/config/organization")
@JResource(code = "organization", descCN = "组织架构", descEN = "Organization",sort = 200)
public class OrganizationController {

    @Autowired
    private IOrganizationService service;

    /**
     * 组织架构树
     * @author jeo_cb
     * @date 2019/10/18
     * @param  dto 查询参数
     * @return 组织架构树
     **/
    @ApiOperation("组织架构树")
    @PostMapping(ControllerConstants.Path.TREE)
    @JOperator(code = ControllerConstants.Operator.TREE, descCN = "组织架构树", descEN = "Organization Tree",sort = 10)
    public CommonResult<List<OrganizationVO>> findOrganizationList(@RequestBody OrganizationQueryDTO dto){
        return service.findOrganizationList(dto);
    }

    /**
     * 组织架构详情
     * @author jeo_cb
     * @date 2019/11/1
     * @param  id 组织架构id
     * @return 组织架构信息
     **/
    @ApiOperation("组织架构详情")
    @GetMapping(ControllerConstants.Path.SINGLE)
    @JOperator(code = ControllerConstants.Operator.SINGLE, descCN = "组织架构详情", descEN = "Organization Detail",sort = 20)
    public CommonResult<OrganizationVO> findOrganization(@Valid Long id){
        return service.findOrganization(id);
    }

    /**
     * 保存组织架构
     * @author jeo_cb
     * @date 2019/10/18
     * @param  dto 保存参数
     * @return 保存结果
     **/
    @JAuditLog
    @ApiOperation("保存组织架构")
    @PutMapping(ControllerConstants.Path.SAVE)
    @JOperator(code = ControllerConstants.Operator.SAVE, descCN = "保存组织架构", descEN = "Save Organization",sort = 30)
    public Result saveOrganization(@RequestBody OrganizationSaveDTO dto){
        return service.saveOrganization(dto);
    }

    /**
     * 删除组织架构
     * @author jeo_cb
     * @date 2019/11/1
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @JAuditLog
    @ApiOperation("删除组织架构")
    @DeleteMapping(ControllerConstants.Path.DELETE)
    @JOperator(code = ControllerConstants.Operator.DELETE, descCN = "删除组织架构", descEN = "Delete Organization",sort = 40)
    public Result deleteOrganization(@Valid @RequestBody BaseIdDTO dto){
        return service.deleteOrganization(dto);
    }
}
