/*
package com.btsoft.jf.cloud.core.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Calendar;

*/
/**
 * TODO 类描述
 *
 * @author jeo_cb
 * @date 2019/9/1
 **//*

@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {

    private final static String ENABLE_FLAG="enableFlag";
    private final static String CREATE_BY="createBy";
    private final static String CREATE_DATE="createDate";
    private final static String LASTUPDATE_BY="lastUpdateBy";
    private final static String LASTUPDATE_DATE="lastUpdateDate";

    @Override
    public void updateFill(MetaObject metaObject) {
        if(metaObject!=null){
            if(metaObject.getValue(LASTUPDATE_BY)==null){
                this.setInsertFieldValByName(LASTUPDATE_BY,-1L,metaObject);
            }
            if(metaObject.getValue(LASTUPDATE_DATE)==null){
                this.setInsertFieldValByName(LASTUPDATE_DATE,Calendar.getInstance().getTime(),metaObject);
            }
        }
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        if(metaObject!=null){
            if(metaObject.getValue(ENABLE_FLAG)==null){
                this.setInsertFieldValByName(ENABLE_FLAG,"Y",metaObject);
            }
            if(metaObject.getValue(CREATE_BY)==null){
                this.setInsertFieldValByName(CREATE_BY,-1L,metaObject);
            }
            if(metaObject.getValue(CREATE_DATE)==null){
                this.setInsertFieldValByName(CREATE_DATE,Calendar.getInstance().getTime(),metaObject);
            }
        }
    }
}
*/
