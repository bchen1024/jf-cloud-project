package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.entity.impl.BatchEntity;
import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.entity.PermissionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限Mapper接口
 * @author jeo_cb
 * @date 2019/10/6
 **/
@Mapper
public interface IPermissionMapper  extends IBaseMapper<PermissionEntity,Long> {
    
    /**
     * 根据角色获取该角色拥有的权限编码
     * @author jeo_cb
     * @date 2019/10/8
     * @param  roleIds 角色id
     * @return 权限编码集合
     **/
    List<String> findPermissionCodeList(@Param("roleIds") List<Long> roleIds);

    /**
     * @author jeo_cb
     * @description 失效已经删除的注解权限点
     * @date 2019/10/11
     * @param batch 批量参数
     * @return 受影响行数
     */
    int inValidPermission(@Param("vo") BatchEntity<PermissionEntity> batch);
}
