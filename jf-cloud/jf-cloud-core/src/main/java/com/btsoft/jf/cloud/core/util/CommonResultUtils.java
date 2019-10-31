package com.btsoft.jf.cloud.core.util;

import com.alibaba.fastjson.JSON;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.ErrorCodeConstants;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 通用结果工具类
 * @author jeo_cb
 * @date 2019/8/30
 **/
public class CommonResultUtils {

    public static <T> CommonResult<T> success(T result){
        CommonResult<T> commonResult=new CommonResult<T>();
        commonResult.setData(result);
        commonResult.setSuccess(true);
        commonResult.setMsg("success");
        commonResult.setCode(ErrorCodeConstants.Global.SUCCESS);
        return commonResult;
    }
    public static Result success(OperationTypeEnum operationType){
        return new Result(operationType.getKey()+".successful",operationType.getCnName()+"成功",true);
    }
    public static Result success(){
        return new Result(OperationTypeEnum.Operate+".successful",OperationTypeEnum.Operate.getCnName()+"成功",true);
    }

    public static <T> CommonResult<PageResult<T>> pageResult(Class<T> clazz, Page page){
        CommonResult<PageResult<T>> commonResult=new CommonResult<>();
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
        return success(JSON.parseObject(JSON.toJSONString(obj),clazz));
    }

    public static <T> CommonResult<List<T>> resultList(Class<T> clazz, Object obj){
        return success(JSON.parseArray(JSON.toJSONString(obj),clazz));
    }

    public static Result fail(String code,Throwable e){
        return new Result(code,e.getMessage(),false);
    }

    public static Result fail(String code,String msg){
        return new Result(code,msg,false);
    }

    public static Result fail(OperationTypeEnum operationType){
        return new Result(operationType.getKey()+".fail",operationType.getCnName()+"失败",false);
    }

    public static Result result(int rows,OperationTypeEnum operationType){
        if(rows>0){
            return success(operationType);
        }
        return fail(operationType);
    }
    public static <T> CommonResult<T> failResult(String code,String msg){
        CommonResult<T> commonResult=new CommonResult<T>();
        commonResult.setSuccess(false);
        commonResult.setMsg(msg);
        commonResult.setCode(code);
        return commonResult;
    }

}
