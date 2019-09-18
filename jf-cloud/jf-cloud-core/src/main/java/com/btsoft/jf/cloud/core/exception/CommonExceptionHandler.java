package com.btsoft.jf.cloud.core.exception;

import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ErrorCodeConstants;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author chenbin
 * @date 2018年6月7日 上午12:25:28
 */
@ControllerAdvice
public class CommonExceptionHandler {

	private final static Logger logger= LoggerFactory.getLogger(CommonExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result handleException(Exception e) {
		logger.error("",e);
		return CommonResultUtils.fail(ErrorCodeConstants.Global.SERVER_ERROR,"服务异常，请联系管理员");
	}

	@ExceptionHandler(CommonException.class)
	@ResponseBody
	public Result handleException(CommonException e) {
		logger.error("",e);
		return CommonResultUtils.fail(e.getCode(),e.getMessage());
	}
	/*
	@ExceptionHandler(ApplicationI18nException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public <T> CommonResult<T> handleException(ApplicationI18nException e) {
		return CommonResultUtils.fail(e.getHttpCode(),e.getErrorCode(), e.getMessage(), e.getArgs(),true);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public <T> CommonResult<T> handleValidException(MethodArgumentNotValidException e) {
		BindingResult bindingResult=e.getBindingResult();
		List<FieldError> errors=bindingResult.getFieldErrors();
		if(!CollectionUtils.isEmpty(errors)){
			FieldError fieldError=errors.get(0);
			return CommonResultUtils.fail(fieldError.getField()+"."+fieldError.getCode(), fieldError.getDefaultMessage());
		}
		return CommonResultUtils.fail(e.getMessage(), e.getMessage());
	}*/
}
