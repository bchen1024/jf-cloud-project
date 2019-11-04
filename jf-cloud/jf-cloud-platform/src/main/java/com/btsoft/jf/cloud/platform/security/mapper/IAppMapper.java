package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.entity.AppEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 应用管理Mapper
 * @author jeo_cb
 * @date 2019/9/7
 **/
@Mapper
public interface IAppMapper extends IBaseMapper<AppEntity,Long> {
    
    /**
     * 更新应用Token
     * @author jeo_cb
     * @date 2019/10/5
     * @param  entity 更新数据
     * @return 受影响行数
     **/
    int updateAppToken(@Param("param") AppEntity entity);
}
