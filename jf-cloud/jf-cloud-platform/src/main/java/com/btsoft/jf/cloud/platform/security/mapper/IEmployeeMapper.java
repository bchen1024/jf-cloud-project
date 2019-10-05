package com.btsoft.jf.cloud.platform.security.mapper;

import com.btsoft.jf.cloud.core.base.mapper.IBaseMapper;
import com.btsoft.jf.cloud.platform.security.dto.employee.EmployeeQueryDTO;
import com.btsoft.jf.cloud.platform.security.entity.EmployeeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工Mapper
 * @author jeo_cb
 * @date 2019/10/5
 **/
@Mapper
public interface IEmployeeMapper extends IBaseMapper<EmployeeEntity> {

    List<EmployeeEntity> findEmployeeList(@Param("vo") EmployeeQueryDTO dto);
}
