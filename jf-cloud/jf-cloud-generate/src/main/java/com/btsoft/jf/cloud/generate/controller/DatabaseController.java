package com.btsoft.jf.cloud.generate.controller;

import com.btsoft.jf.cloud.generate.dto.DatabaseQueryDTO;
import com.btsoft.jf.cloud.generate.service.IDatabaseService;
import com.btsoft.jf.cloud.generate.vo.DatabaseVO;
import com.btsoft.jf.core.pojo.result.impl.CommonResult;
import com.btsoft.jf.core.pojo.result.impl.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据库Controller
 * @author jeo_cb
 * @date 2020/1/13
 **/
@RestController
@RequestMapping("/database")
@Api(tags = "数据源Controller")
public class DatabaseController {

    @Autowired
    private IDatabaseService databaseService;

    @PostMapping("/page")
    @ApiOperation("分页列表")
    public CommonResult<PageResult<DatabaseVO>> getPageList(@RequestBody DatabaseQueryDTO dto){
        return databaseService.getPageList(dto);
    }

    @GetMapping("/get/{id}")
    @ApiOperation("单个查询")
    public CommonResult<DatabaseVO> getById(@PathVariable Long id){
        return databaseService.getById(id);
    }

    @PostMapping("/getByIds")
    @ApiOperation("多个查询")
    public CommonResult<List<DatabaseVO>> getListByIds(@RequestBody List<Long> ids){
        return databaseService.getListByIds(ids);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("单个删除")
    public CommonResult<Integer> deleteById(@PathVariable Long id){
        return databaseService.deleteById(id);
    }

    @DeleteMapping("/delete")
    @ApiOperation("批量删除")
    public CommonResult<Integer> deleteByIds(@RequestBody List<Long> ids){
        return databaseService.deleteByIds(ids);
    }
}