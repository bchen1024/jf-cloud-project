package com.btsoft.jf.cloud.platform.security.service.impl;

import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.core.util.EntityUtils;
import com.btsoft.jf.cloud.platform.security.dto.employee.EmployeeQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.employee.EmployeeUpdateDTO;
import com.btsoft.jf.cloud.platform.security.entity.EmployeeEntity;
import com.btsoft.jf.cloud.platform.security.mapper.IEmployeeMapper;
import com.btsoft.jf.cloud.platform.security.service.IEmployeeService;
import com.btsoft.jf.cloud.platform.security.vo.employee.EmployeeVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 员工Service实现
 * @author jeo_cb
 * @date 2019/10/5
 **/
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeMapper mapper;

    @Override
    public CommonResult<PageResult<EmployeeVO>> findEmployeePage(EmployeeQueryDTO dto) {
        Page page= PageHelper.startPage(dto.getCurPage(),dto.getPageSize(),true);
        mapper.findEmployeeList(dto);
        return CommonResultUtils.pageResult(EmployeeVO.class,page);
    }

    @Override
    public CommonResult<EmployeeVO> findEmployee(Long id) {
        EmployeeEntity entity=new EmployeeEntity();
        entity.setUserId(id);
        entity=mapper.findSingle(entity);
        EmployeeVO employeeVO=new EmployeeVO();
        BeanUtils.copyProperties(entity,employeeVO);
        return CommonResultUtils.success(employeeVO);
    }

    @Override
    public Result updateEmployee(EmployeeUpdateDTO dto) {
        EmployeeEntity entity=EntityUtils.dtoToEntity(EmployeeEntity.class,dto);
        int rows=mapper.updateSingle(entity);
        return CommonResultUtils.result(rows, OperationTypeEnum.Update);
    }
}
