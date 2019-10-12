package com.btsoft.jf.cloud.monitor.service.impl;

import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.monitor.dto.AuditLogCreateDTO;
import com.btsoft.jf.cloud.monitor.dto.AuditLogQueryDTO;
import com.btsoft.jf.cloud.monitor.entity.AuditLogEntity;
import com.btsoft.jf.cloud.monitor.mapper.IAuditLogMapper;
import com.btsoft.jf.cloud.monitor.service.IAuditLogService;
import com.btsoft.jf.cloud.monitor.vo.AuditLogVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author chenbin
 * @date 2018年6月16日 下午10:45:49
 */
@Service
public class AuditLogServiceImpl implements IAuditLogService {
	
	@Autowired
	private IAuditLogMapper mapper;

	/**
	 * 创建审计日志
	 * @author jeo_cb
	 * @date 2019/10/12
	 * @param dto 创建参数
	 * @return 创建结果
	 **/
	@Override
	public Result createAuditLog(AuditLogCreateDTO dto) {
		AuditLogEntity entity=new AuditLogEntity();
		BeanUtils.copyProperties(dto,entity);
		int rows=mapper.createSingle(entity);
		return CommonResultUtils.result(rows, OperationTypeEnum.Create);
	}

	/**
	 * 分页查询审计日志
	 * @author jeo_cb
	 * @date 2019/10/12
	 * @param dto 查询参数
	 * @return 查询结果
	 **/
	@Override
	public CommonResult<PageResult<AuditLogVO>> findAuditLogPage(AuditLogQueryDTO dto) {
		Page page= PageHelper.startPage(dto.getCurPage(),dto.getPageSize(),true);
		mapper.findAuditLogPage(dto);
		return CommonResultUtils.pageResult(AuditLogVO.class,page);
	}
}
