package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdAppDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupSaveDTO;
import com.btsoft.jf.cloud.platform.security.service.IGroupService;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 群组管理Controller
 * @author jeo_cb
 * @date 2019/8/28
 **/
@RestController
@RequestMapping("/security/group")
@Api(tags = "群组管理")
public class GroupController {

    @Autowired
    private IGroupService service;

    /**
     * 群组保存，存在群组id则修改，不存在则新增
     * @author jeo_cb
     * @date 2019/9/1
     * @param  dto 群组包存参数
     * @return 保存结果
     **/
    @PutMapping("/save")
    @ApiOperation("保存群组，创建或者更新")
    public Result saveGroup(@RequestBody GroupSaveDTO dto){
        return service.saveGroup(dto);
    }

    /**
     * 删除单个群组
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @DeleteMapping("/delete")
    @ApiOperation("删除群组")
    public Result deleteGroup(@RequestBody BaseIdAppDTO dto){
        return service.deleteGroup(dto);
    }

    /**
     * 根据群组id获取单个群组
     * @author jeo_cb
     * @date 2019/9/5
     * @param  id 群组
     * @return 群组信息
     **/
    @GetMapping("/single")
    @ApiOperation("根据群组id获取单个群组")
    public CommonResult<GroupVO> findRole(Long id){
        return service.findGroup(id);
    }

    /**
     * 分页查询群组列表
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 查询参数
     * @return 群组分页列表
     **/
    @PostMapping("/page")
    @ApiOperation("分页查询群组")
    public CommonResult<PageResult<GroupVO>> findGroupPage(@RequestBody GroupQueryDTO dto){
        return service.findGroupPage(dto);
    }
}
