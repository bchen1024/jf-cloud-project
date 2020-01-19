package com.btsoft.jf.core.exception;

import com.btsoft.jf.core.constant.CodeConstants;
import com.btsoft.jf.core.pojo.result.impl.Result;
import com.btsoft.jf.core.util.CommonResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
		return CommonResultUtils.fail(CodeConstants.Global.ERROR,"服务异常，请联系管理员");
	}

	@ExceptionHandler(CommonException.class)
	@ResponseBody
	public Result handleException(CommonException e) {
		logger.error("",e);
		return CommonResultUtils.fail(e.getCode(),e.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public Result handleValidException(MethodArgumentNotValidException e) {
		BindingResult bindingResult=e.getBindingResult();
		List<FieldError> errors=bindingResult.getFieldErrors();
		if(!CollectionUtils.isEmpty(errors)){
			FieldError fieldError=errors.get(0);
			//List<String> errorList=new ArrayList<>();
			return CommonResultUtils.fail(fieldError.getField()+"."+fieldError.getCode(), fieldError.getDefaultMessage());
		}
		return CommonResultUtils.fail(e.getMessage(), e.getMessage());
	}
}
