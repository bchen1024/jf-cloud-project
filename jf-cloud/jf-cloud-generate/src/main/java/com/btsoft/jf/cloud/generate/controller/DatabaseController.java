package com.btsoft.jf.cloud.generate.controller;

import com.btsoft.jf.cloud.generate.dto.DatabaseQueryDTO;
import com.btsoft.jf.cloud.generate.service.IDatabaseService;
import com.btsoft.jf.cloud.generate.vo.DatabaseVO;
import com.btsoft.jf.core.base.result.impl.CommonResult;
import com.btsoft.jf.core.base.result.impl.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 数据库Controller
 * @author jeo_cb
 * @date 2020/1/13
 **/
@RestController
@RequestMapping("/database")
public class DatabaseController {

    @Autowired
    private IDatabaseService databaseService;

    @PostMapping("/page")
    public CommonResult<PageResult<DatabaseVO>> getPageList(@RequestBody DatabaseQueryDTO dto){
        return databaseService.getPageList(dto);
    }

    @GetMapping("/get/{id}")
    public CommonResult<DatabaseVO> getById(@PathVariable Long id){
        return databaseService.getById(id);
    }
}