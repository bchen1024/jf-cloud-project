package com.btsoft.jf.cloud.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.btsoft.jf.cloud.core.base.dto.IBaseDTO;
import com.btsoft.jf.cloud.core.base.dto.ISearchKeyword;

/**
 * TODO 类描述
 *
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

    public static <T> T baseDtoToEntity(Class<T> clazz, IBaseDTO dto)  {
        T entity=JSON.parseObject(JSON.toJSONString(dto),clazz);
        return entity;
    }
}
