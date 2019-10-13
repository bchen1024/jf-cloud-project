package com.btsoft.jf.cloud.core.util;

import com.alibaba.fastjson.JSON;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ErrorCodeConstants;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.github.pagehelper.Page;

/**
 * 通用结果工具类
 * @author jeo_cb
 * @date 2019/8/30
 **/
public class CommonResultUtils {

    public static <T> CommonResult<T> success(T result){
        CommonResult commonResult=new CommonResult();
        commonResult.setData(result);
        commonResult.setSuccess(true);
        commonResult.setMsg("success");
        commonResult.setCode(ErrorCodeConstants.Global.SUCCESS);
        return commonResult;
    }

    public static Result fail(String code,Throwable e){
        Result result=new Result(code,e.getMessage(),false);
        return result;
    }

    public static Result fail(String code,String msg){
        Result result=new Result(code,msg,false);
        return result;
    }

    public static Result success(OperationTypeEnum operationType){
        Result result=new Result(operationType.getKey()+".successful",operationType.getCnName()+"成功",true);
        return result;
    }

    public static Result fail(OperationTypeEnum operationType){
        Result result=new Result(operationType.getKey()+".fail",operationType.getCnName()+"失败",false);
        return result;
    }

    public static Result result(int rows,OperationTypeEnum operationType){
        if(rows>0){
            return success(operationType);
        }
        return fail(operationType);
    }

    public static <T> CommonResult<PageResult<T>> pageResult(Class<T> clazz,Page page){
        CommonResult commonResult=new CommonResult();
        PageResult<T> pageResult=new PageResult<>();
        pageResult.setCurPage(page.getPageNum());
        pageResult.setPageSize(page.getPageSize());
        pageResult.setTotal(page.getTotal());
        pageResult.setTotalPage(page.getPages());
        pageResult.setList(JSON.parseArray(JSON.toJSONString(page.getResult()),clazz));
        commonResult.setData(pageResult);
        commonResult.setSuccess(true);
        commonResult.setMsg("success");
        commonResult.setCode(ErrorCodeConstants.Global.SUCCESS);
        return commonResult;
    }

    public static <T> CommonResult<T> result(Class<T> clazz,Object obj){
        return CommonResultUtils.success(JSON.parseObject(JSON.toJSONString(obj),clazz));
    }
}
