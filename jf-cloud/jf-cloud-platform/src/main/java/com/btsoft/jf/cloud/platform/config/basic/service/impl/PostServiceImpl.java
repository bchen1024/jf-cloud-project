package com.btsoft.jf.cloud.platform.config.basic.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.core.util.EntityUtils;
import com.btsoft.jf.cloud.platform.config.basic.dto.post.PostQueryDTO;
import com.btsoft.jf.cloud.platform.config.basic.dto.post.PostSaveDTO;
import com.btsoft.jf.cloud.platform.config.basic.entity.PostEntity;
import com.btsoft.jf.cloud.platform.config.basic.mapper.IPostMapper;
import com.btsoft.jf.cloud.platform.config.basic.service.IPostService;
import com.btsoft.jf.cloud.platform.config.basic.vo.post.PostVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位Service实现类
 * @author jeo_cb
 * @date 2019/11/15
 **/
@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    private IPostMapper mapper;
    @Override
    public CommonResult<PageResult<PostVO>> findPostPage(PostQueryDTO dto) {
        PostEntity entity= EntityUtils.queryDtoToEntity(PostEntity.class,dto);
        Page page= PageHelper.startPage(dto.getCurPage(),dto.getPageSize(),true);
        mapper.findList(entity);
        return CommonResultUtils.pageResult(PostVO.class,page);
    }

    @Override
    public CommonResult<PostVO> findPost(Long id) {
        PostEntity entity=mapper.findSingleById(id);
        return CommonResultUtils.result(PostVO.class,entity);
    }

    @Override
    public Result savePost(PostSaveDTO dto) {
        PostEntity entity=EntityUtils.dtoToEntity(PostEntity.class,dto);
        int rows;
        if(dto.getPostId()!=null){
            rows=mapper.updateSingle(entity);
        }else{
            rows=mapper.createSingle(entity);
        }
        return CommonResultUtils.result(rows, OperationTypeEnum.Save);
    }

    @Override
    public Result deletePost(BaseIdDTO dto) {
        int rows=mapper.deleteSingleById(dto.getId());
        //TODO 删除岗位关联数据
        return CommonResultUtils.result(rows, OperationTypeEnum.Delete);
    }

    @Override
    public CommonResult<List<PostVO>> findPostList(PostQueryDTO dto) {
        PostEntity entity= EntityUtils.queryDtoToEntity(PostEntity.class,dto);
        List<PostEntity> list=mapper.findList(entity);
        return CommonResultUtils.resultList(PostVO.class,list);
    }
}
