package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupSaveDTO;
import com.btsoft.jf.cloud.platform.security.service.IGroupService;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupBaseVO;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 群组管理Controller
 * @author jeo_cb
 * @date 2019/8/28
 **/
@RestController
@Api(tags = "群组管理")
@RequestMapping("/security/group")
@JResource(code = "group", descCN = "群组管理", descEN = "Group",sort = 50)
public class GroupController {

    @Autowired
    private IGroupService service;

    /**
     * 分页查询群组列表
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 查询参数
     * @return 群组分页列表
     **/
    @ApiOperation("分页查询群组")
    @PostMapping(ControllerConstants.Path.PAGE)
    @JOperator(code = ControllerConstants.Operator.PAGE, descCN = "群组列表", descEN = "Group List",sort = 10)
    public CommonResult<PageResult<GroupVO>> findGroupPage(@RequestBody GroupQueryDTO dto){
        return service.findGroupPage(dto);
    }

    /**
     * 根据群组id获取单个群组
     * @author jeo_cb
     * @date 2019/9/5
     * @param  id 群组
     * @return 群组信息
     **/
    @ApiOperation("群组详情")
    @GetMapping(ControllerConstants.Path.SINGLE)
    @JOperator(code = ControllerConstants.Operator.SINGLE, descCN = "群组详情", descEN = "Group Detail",sort = 20)
    public CommonResult<GroupVO> findGroup(@Valid Long id){
        return service.findGroup(id);
    }

    /**
     * 群组保存，存在群组id则修改，不存在则新增
     * @author jeo_cb
     * @date 2019/9/1
     * @param  dto 群组包存参数
     * @return 保存结果
     **/
    @JAuditLog
    @ApiOperation("保存群组，创建或者更新")
    @PutMapping(ControllerConstants.Path.SAVE)
    @JOperator(code = ControllerConstants.Operator.SAVE, descCN = "保存群组", descEN = "Save Group",sort = 30)
    public Result saveGroup(@Valid @RequestBody GroupSaveDTO dto){
        return service.saveGroup(dto);
    }

    /**
     * 删除单个群组
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @JAuditLog
    @ApiOperation("删除群组")
    @DeleteMapping(ControllerConstants.Path.DELETE)
    @JOperator(code = ControllerConstants.Operator.DELETE, descCN = "删除群组", descEN = "Delete Group",sort = 40)
    public Result deleteGroup(@Valid @RequestBody BaseIdDTO dto){
        return service.deleteGroup(dto);
    }

    /**
     * 获取可选择的群组
     * @author jeo_cb
     * @date 2019/11/4
     * @return 群组列表
     **/
    @ApiOperation("选择群组")
    @PostMapping("/select")
    public CommonResult<List<GroupBaseVO>> findGroupList(@RequestBody GroupQueryDTO dto){
        return service.findGroupList(dto);
    }
}
