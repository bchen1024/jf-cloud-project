package com.btsoft.jf.cloud.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.btsoft.jf.cloud.core.base.dto.ISearchKeyword;

import java.util.List;

/**
 * 实体工具类
 * @author jeo_cb
 * @date 2019/9/7
 **/
public class EntityUtils {

    public static <T> T queryDtoToEntity(Class<T> clazz, ISearchKeyword dto)  {
        String json= JSON.toJSONString(dto);
        JSONObject jsonObj=JSON.parseObject(json);
        if(dto.getKeywordValue()!=null && dto.getKeywordField()!=null){
            jsonObj.put(dto.getKeywordField(),dto.getKeywordValue());
        }
        T entity=JSON.parseObject(jsonObj.toJSONString(),clazz);
        return entity;
    }

    public static <T> T dtoToEntity(Class<T> clazz, Object dto)  {
        T entity=JSON.parseObject(JSON.toJSONString(dto),clazz);
        return entity;
    }

    public static <T> List<T> entityToList(Class<T> clazz,Object obj){
        return JSON.parseArray(JSON.toJSONString(obj),clazz);
    }
}
