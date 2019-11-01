package com.btsoft.jf.cloud.monitor.controller;

import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ControllerConstants;
import com.btsoft.jf.cloud.monitor.dto.AuditLogCreateDTO;
import com.btsoft.jf.cloud.monitor.dto.AuditLogQueryDTO;
import com.btsoft.jf.cloud.monitor.service.IAuditLogService;
import com.btsoft.jf.cloud.monitor.vo.AuditLogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author chenbin
 * @date 2018年6月16日 下午11:46:23
 */
@RestController
@Api(tags = "审计日志")
@RequestMapping("/monitor/auditLog")
@JResource(code = "auditLog", descCN = "审计日志", descEN = "Audit Log",appCode="jf-cloud-platform",sort = 1000)
public class AuditLogController {
	
	@Autowired
	private IAuditLogService service;

	/**
	 * 分页查询审计日志
	 * @author jeo_cb
	 * @date 2019/10/12
	 * @param dto 查询参数
	 * @return 查询结果
	 **/
	@ApiOperation("分页查询审计日志")
	@PostMapping(ControllerConstants.Path.PAGE)
	@JOperator(code = ControllerConstants.Operator.PAGE, descCN = "日志列表", descEN = "AuditLog List",sort = 10)
	public CommonResult<PageResult<AuditLogVO>> findAuditLogPage(@RequestBody AuditLogQueryDTO dto){
		return service.findAuditLogPage(dto);
	}

	/**
	 * 创建审计日志
	 * @author jeo_cb
	 * @date 2019/10/12
	 * @param dto 创建参数
	 * @return 创建结果
	 **/
	@ApiOperation("创建审计日志")
	@PostMapping(ControllerConstants.Path.CREATE)
	public Result createAuditLog(@RequestBody AuditLogCreateDTO dto) {
		return service.createAuditLog(dto);
	}
}
