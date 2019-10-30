package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.entity.GroupEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 群组Mapper接口
 * @author jeo_cb
 * @date 2019/9/30 15:24
 **/
@Mapper
public interface IGroupMapper extends IBaseMapper<GroupEntity> {
}
