package com.btsoft.jf.cloud.platform.security.controller;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdAppDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleSaveDTO;
import com.btsoft.jf.cloud.platform.security.service.IRoleService;
import com.btsoft.jf.cloud.platform.security.vo.role.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Role管理Controller
 * @author jeo_cb
 * @date 2019/8/28
 **/
@RestController
@RequestMapping("/security/role")
@Api(tags = "角色管理")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 角色保存，存在角色id则修改，不存在则新增
     * @author jeo_cb
     * @date 2019/9/1
     * @param  dto 角色包存参数
     * @return 保存结果
     **/
    @PutMapping("/save")
    @ApiOperation("保存角色，创建或者更新")
    public Result saveRole(@RequestBody RoleSaveDTO dto){
        return roleService.saveRole(dto);
    }

    /**
     * 删除单个角色
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @DeleteMapping("/delete")
    @ApiOperation("删除角色")
    public Result deleteRole(@RequestBody BaseIdAppDTO dto){
        return roleService.deleteRole(dto);
    }

    /**
     * 根据角色id获取单个角色
     * @author jeo_cb
     * @date 2019/9/5
     * @param  id 角色
     * @return 角色信息
     **/
    @GetMapping("/single")
    @ApiOperation("根据角色id获取单个角色")
    public CommonResult<RoleVO> findRole(Long id){
        return roleService.findRole(id);
    }

    /**
     * 分页查询角色列表
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 查询参数
     * @return 角色分页列表
     **/
    @PostMapping("/page")
    @ApiOperation("分页查询角色")
    public CommonResult<PageResult<RoleVO>> findRolePage(@RequestBody RoleQueryDTO dto){
        return roleService.findRolePage(dto);
    }
}
