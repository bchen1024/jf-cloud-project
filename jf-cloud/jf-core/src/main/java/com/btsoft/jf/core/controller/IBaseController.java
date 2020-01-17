package com.btsoft.jf.core.controller;

import com.btsoft.jf.core.pojo.result.impl.CommonResult;
import com.btsoft.jf.core.pojo.result.impl.PageResult;

import java.io.Serializable;

/**
 * 基类Controller
 * @author jeo_cb
 * @date 2020/1/17 17:32
 **/
public interface IBaseController{

  CommonResult<PageResult<?>> getPageList(Serializable dto);
}
