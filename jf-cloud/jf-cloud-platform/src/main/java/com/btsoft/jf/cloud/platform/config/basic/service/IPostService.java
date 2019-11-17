package com.btsoft.jf.cloud.platform.config.basic.service;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.config.basic.dto.post.PostQueryDTO;
import com.btsoft.jf.cloud.platform.config.basic.dto.post.PostSaveDTO;
import com.btsoft.jf.cloud.platform.config.basic.vo.post.PostVO;

import java.util.List;

/**
 * 岗位Service接口
 * @author jeo_cb
 * @date 2019/11/15
 **/
public interface IPostService {

    /**
     * 岗位分页列表
     * @author jeo_cb
     * @date 2019/11/15
     * @param  dto 查询参数
     * @return 岗位分页列表
     **/
    CommonResult<PageResult<PostVO>> findPostPage(PostQueryDTO dto);

    /**
     * 单个岗位
     * @author jeo_cb
     * @date 2019/11/15
     * @param  id 岗位id
     * @return 岗位
     **/
    CommonResult<PostVO> findPost(Long id);

    /**
     * 保存岗位
     * @author jeo_cb
     * @date 2019/11/15
     * @param  dto 保存参数
     * @return 保存结果
     **/
    Result savePost(PostSaveDTO dto);

    /**
     * 删除岗位
     * @author jeo_cb
     * @date 2019/11/15
     * @param  dto 删除参数
     * @return 删除结果
     **/
    Result deletePost(BaseIdDTO dto);

    /**
     * 岗位选择
     * @author jeo_cb
     * @date 2019/11/16
     * @param  dto 查询参数
     * @return 岗位列表
     **/
    CommonResult<List<PostVO>> findPostList(PostQueryDTO dto);
}
