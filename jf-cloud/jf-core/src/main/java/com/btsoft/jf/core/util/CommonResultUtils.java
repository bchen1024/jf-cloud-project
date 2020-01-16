package com.btsoft.jf.core.util;

import com.alibaba.fastjson.JSON;
import com.btsoft.jf.core.base.page.impl.PageVO;
import com.btsoft.jf.core.base.result.impl.CommonResult;
import com.btsoft.jf.core.base.result.impl.PageResult;
import com.btsoft.jf.core.base.result.impl.Result;
import com.btsoft.jf.core.constant.CodeConstants;
import com.github.pagehelper.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用结果工具类
 * @author jeo_cb
 * @date 2019/8/30
 **/
public class CommonResultUtils {

    /**
     * 成功结果
     * @author jeo_cb
     * @date 2020/1/13
     * @param  result 结果
     * @return 通用成功结果
     **/
    public static <T> CommonResult<T> success(T result){
        CommonResult<T> commonResult=new CommonResult<>();
        commonResult.setData(result);
        commonResult.setSuccess(true);
        commonResult.setCode(CodeConstants.Global.SUCCESS);
        return commonResult;
    }

    /**
     * 失败结果
     * @author jeo_cb
     * @date 2020/1/14
     * @param  code 错误码
     * @param msg 错误信息
     * @param args 错误参数
     * @return 错误结果
     **/
    public static Result fail(String code,String msg,Object... args){
        Result result=new Result();
        result.setSuccess(false);;
        result.setCode(code);
        result.setMsg(msg);
        result.setArgs(args);
        return result;
    }

    /**
     * 分页结果
     * @author jeo_cb
     * @date 2020/1/13
     * @param  clazz 分页结果class
     * @param page 分页对象
     * @return 分页结果
     **/
    public static <T> CommonResult<PageResult<T>> pageResult(Class<T> clazz, Page page){
        CommonResult<PageResult<T>> commonResult=new CommonResult<>();
        PageResult<T> pageResult=new PageResult<>();
        PageVO pageVO=new PageVO();
        pageVO.setCurPage(page.getPageNum());
        pageVO.setPageSize(page.getPageSize());
        pageVO.setTotal(page.getTotal());
        pageVO.setTotalPage(page.getPages());
        pageResult.setPage(pageVO);
        pageResult.setList(JSON.parseArray(JSON.toJSONString(page.getResult()),clazz));
        commonResult.setData(pageResult);
        commonResult.setSuccess(true);
        commonResult.setCode(CodeConstants.Global.SUCCESS);
        return commonResult;
    }

    /**
     * 通用结果
     * @author jeo_cb
     * @date 2020/1/14
     * @param  clazz 结果对象class
     * @param obj 结果数据
     * @return 通用结果
     **/
    public static <T> CommonResult<T> result(Class<T> clazz,Object obj){
        return success(JSON.parseObject(JSON.toJSONString(obj),clazz));
    }

    /**
     * 通用结果列表
     * @author jeo_cb
     * @date 2020/1/14
     * @param  clazz 结果对象class
     * @param obj 结果数据
     * @return 通用结果列表
     **/
    public static <T> CommonResult<List<T>> resultList(Class<T> clazz, Object obj){
        return success(JSON.parseArray(JSON.toJSONString(obj),clazz));
    }

    /**
     * 通用结果列表
     * @author jeo_cb
     * @date 2020/1/14T
     * @return 通用结果列表
     **/
    public static <T> CommonResult<List<T>> emptyList(){
        return success(new ArrayList<T>());
    }

    public static <T> CommonResult<T> result(int rows, T obj){
        if(rows>0){
            return success(obj);
        }
        //TODO
        return null;
    }
}
