package com.btsoft.jf.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.btsoft.jf.core.base.dto.ISearchKeywordPageDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * 对象工具
 * @author jeo_cb
 * @date 2020/1/13
 **/
public class BeanUtils {

    private final static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    /**
     * DTO转换成Entity
     * @author jeo_cb
     * @date 2020/1/13
     * @param  clazz 实体class
     * @param dto 转换对象
     * @return 实体对象
     **/
    public static <T> T  dtoToEntity(Class<T> clazz, ISearchKeywordPageDTO dto){
        String json= JSON.toJSONString(dto);
        JSONObject jsonObj=JSON.parseObject(json);
        if(dto.getKeywordValue()!=null && dto.getKeywordField()!=null){
            try{
                Field field=FieldUtils.getField(clazz,dto.getKeywordField(),true);
                if(field!=null){
                    logger.error(field.getGenericType().getClass().toString());
                    if(Number.class.isAssignableFrom(field.getGenericType().getClass())){
                        if(StringUtils.isNumeric(dto.getKeywordValue().toString())){
                            jsonObj.put(dto.getKeywordField(),dto.getKeywordValue());
                        }else{
                            jsonObj.put(dto.getKeywordField(),-1);
                        }
                    }else{
                        jsonObj.put(dto.getKeywordField(),dto.getKeywordValue());
                    }
                }
            }catch (Exception ex){
                logger.error("关键字段不存在",ex);
            }
        }

        return JSON.parseObject(jsonObj.toJSONString(),clazz);
    }

    /**
     * DTO转换成Entity
     * @author jeo_cb
     * @date 2020/1/13
     * @param  clazz 实体class
     * @param dto 转换对象
     * @return 实体对象
     **/
    public static <T> T  dtoToEntity(Class<T> clazz, Object dto){
        String json= JSON.toJSONString(dto);
        JSONObject jsonObj=JSON.parseObject(json);
        return JSON.parseObject(jsonObj.toJSONString(),clazz);
    }
}
