package com.btsoft.jf.cloud.platform.config.basic.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.config.basic.entity.PostEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工职位Mapper
 * @author jeo_cb
 * @date 2019/10/7
 **/
@Mapper
public interface IPostMapper extends IBaseMapper<PostEntity,Long> {
}
