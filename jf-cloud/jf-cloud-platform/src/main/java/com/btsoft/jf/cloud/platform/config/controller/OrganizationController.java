package com.btsoft.jf.cloud.platform.config.controller;

import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerContants;
import com.btsoft.jf.cloud.platform.config.dto.organization.OrganizationQueryDTO;
import com.btsoft.jf.cloud.platform.config.dto.organization.OrganizationSaveDTO;
import com.btsoft.jf.cloud.platform.config.service.IOrganizationService;
import com.btsoft.jf.cloud.platform.config.vo.organization.OrganizationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(ControllerContants.Path.TREE)
    @JOperator(code = ControllerContants.Operator.TREE, descCN = "组织架构树", descEN = "Organization Tree",sort = 10)
    public CommonResult<List<OrganizationVO>> findOrganizationList(@RequestBody OrganizationQueryDTO dto){
        return service.findOrganizationList(dto);
    }

    /**
     * 保存组织架构
     * @author jeo_cb
     * @date 2019/10/18
     * @param  dto 保存参数
     * @return 保存结果
     **/
    @ApiOperation("保存组织架构")
    @PutMapping(ControllerContants.Path.SAVE)
    @JOperator(code = ControllerContants.Operator.SAVE, descCN = "保存组织架构", descEN = "Save Organization",sort = 20)
    public Result saveOrganization(@RequestBody OrganizationSaveDTO dto){
        return service.saveOrganization(dto);
    }
}
