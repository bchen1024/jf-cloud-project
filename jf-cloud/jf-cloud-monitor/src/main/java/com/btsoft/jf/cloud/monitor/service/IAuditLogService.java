package com.btsoft.jf.cloud.monitor.service;

import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.monitor.dto.AuditLogCreateDTO;
import com.btsoft.jf.cloud.monitor.dto.AuditLogQueryDTO;
import com.btsoft.jf.cloud.monitor.vo.AuditLogVO;

/**
 * 
 * @author chenbin
 * @date 2018年6月16日 下午10:45:03
 */
public interface IAuditLogService{

	/**
	 * 创建审计日志
	 * @author jeo_cb
	 * @date 2019/10/12
	 * @param dto 创建参数
	 * @return 创建结果
	 **/
	Result createAuditLog(AuditLogCreateDTO dto);

	/**
	 * 分页查询审计日志
	 * @author jeo_cb
	 * @date 2019/10/12
	 * @param dto 查询参数
	 * @return 查询结果
	 **/
	CommonResult<PageResult<AuditLogVO>> findAuditLogPage(AuditLogQueryDTO dto);

}
