package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.entity.GroupUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 群组用户Mapper接口
 * @author jeo_cb
 * @date 2019/10/30
 **/
@Mapper
public interface IGroupUserMapper extends IBaseMapper<GroupUserEntity,Long> {

    /**
     * 查询群组用户列表
     * @author jeo_cb
     * @date 2019/10/30
     * @param  dto 查询参数
     * @return 群组用户列表
     **/
    List<GroupUserEntity> findGroupUserList(@Param("param") GroupUserQueryDTO dto);

    /**
     * 根据群组id删除
     * @author jeo_cb
     * @date 2019/11/4
     * @param groupId 群组id
     * @return 受影响行数
     */
    int deleteByGroupId(@Param("groupId") Long groupId);
}
