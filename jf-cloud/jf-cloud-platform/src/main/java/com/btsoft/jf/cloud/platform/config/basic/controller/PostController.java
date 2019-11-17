package com.btsoft.jf.cloud.platform.config.basic.controller;

import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.platform.config.basic.dto.post.PostQueryDTO;
import com.btsoft.jf.cloud.platform.config.basic.dto.post.PostSaveDTO;
import com.btsoft.jf.cloud.platform.config.basic.service.IPostService;
import com.btsoft.jf.cloud.platform.config.basic.vo.post.PostVO;
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
@Api(tags = "岗位配置")
@RequestMapping("/config/post")
@JResource(code = "post", descCN = "岗位配置", descEN = "Post",sort = 200)
public class PostController {

    @Autowired
    private IPostService service;

    /**
     * 岗位分页列表
     * @author jeo_cb
     * @date 2019/11/15
     * @param  dto 查询参数
     * @return 岗位分页列表
     **/
    @ApiOperation("岗位分页列表")
    @PostMapping(ControllerConstants.Path.PAGE)
    @JOperator(code = ControllerConstants.Operator.PAGE, descCN = "岗位列表", descEN = "Post List",sort = 10)
    public CommonResult<PageResult<PostVO>> findPostPage(@RequestBody PostQueryDTO dto){
        return service.findPostPage(dto);
    }

    /**
     * 单个岗位
     * @author jeo_cb
     * @date 2019/11/15
     * @param  id 岗位id
     * @return 岗位
     **/
    @ApiOperation("岗位详情")
    @GetMapping(ControllerConstants.Path.SINGLE)
    @JOperator(code = ControllerConstants.Operator.SINGLE, descCN = "岗位详情", descEN = "Post Detail",sort = 20)
    public CommonResult<PostVO> findPost(@Valid Long id){
        return service.findPost(id);
    }

    /**
     * 保存岗位
     * @author jeo_cb
     * @date 2019/11/15
     * @param  dto 保存参数
     * @return 保存结果
     **/
    @JAuditLog
    @ApiOperation("保存岗位，创建或者更新")
    @PutMapping(ControllerConstants.Path.SAVE)
    @JOperator(code = ControllerConstants.Operator.SAVE, descCN = "保存岗位", descEN = "Save Post",sort = 30)
    public Result savePost(@Valid @RequestBody PostSaveDTO dto){
        return service.savePost(dto);
    }

    /**
     * 删除岗位
     * @author jeo_cb
     * @date 2019/11/15
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @JAuditLog
    @ApiOperation("删除岗位")
    @DeleteMapping(ControllerConstants.Path.DELETE)
    @JOperator(code = ControllerConstants.Operator.DELETE, descCN = "删除岗位", descEN = "Delete Post",sort = 40)
    public Result deletePost(@Valid @RequestBody BaseIdDTO dto){
        return service.deletePost(dto);
    }

    @ApiOperation("岗位选择")
    @PostMapping(ControllerConstants.Path.SELECT)
    public CommonResult<List<PostVO>> findPostList(@RequestBody PostQueryDTO dto){
        return service.findPostList(dto);
    }
}
