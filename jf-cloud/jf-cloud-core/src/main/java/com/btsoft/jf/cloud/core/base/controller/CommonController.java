package com.btsoft.jf.cloud.core.base.controller;

import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.service.ICommonService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 通用Controller
 * @author jeo_cb
 * @date 2019/10/5
 **/
@RestController
@RequestMapping("/common")
@Api(tags = "通用服务")
public class CommonController {
	
	@Autowired
	private ICommonService service;
	
	@GetMapping("/permission/list")
	public CommonResult<List<Map<String,String>>> listPermission(){
		return service.listPermission();
	}

}
