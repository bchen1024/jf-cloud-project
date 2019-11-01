package com.btsoft.jf.cloud.monitor.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.monitor.dto.AuditLogQueryDTO;
import com.btsoft.jf.cloud.monitor.entity.AuditLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 审计日志Mapper
 * @author jeo_cb
 * @date 2019/10/12 10:32
 **/
@Mapper
public interface IAuditLogMapper extends IBaseMapper<AuditLogEntity,Long> {

    /**
     * 分页查询审计日志
     * @author jeo_cb
     * @date 2019/10/12
     * @param dto 查询参数
     * @return 查询结果
     **/
    List<AuditLogEntity> findAuditLogPage(@Param("vo") AuditLogQueryDTO dto);
}
