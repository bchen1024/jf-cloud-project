package com.btsoft.jf.core.mapper.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.btsoft.jf.core.context.impl.JfSupport;
import com.btsoft.jf.core.mapper.IMetaObjectHandler;
import com.btsoft.jf.core.mapper.annotation.Column;
import com.btsoft.jf.core.mapper.annotation.Id;
import com.btsoft.jf.core.mapper.annotation.Table;
import com.btsoft.jf.core.mapper.enums.MapperMethod;
import com.btsoft.jf.core.mapper.metadata.ColumnMetadata;
import com.btsoft.jf.core.mapper.metadata.TableMetadata;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * sql供应支持
 * @author jeo_cb
 * @date 2020/1/17 10:35
 **/
public class SqlProviderSupport {

    private final static Logger logger= LoggerFactory.getLogger(SqlProviderSupport.class);

    private final static Map<Class<?>, TableMetadata> TABLE_CACHE =new ConcurrentHashMap<>(64);

    private final static String ALL_COLUMN=" * ";

    private final static String COUNT="COUNT(0)";

    private final static String AS=" AS ";

    private final static String VALUE="#{%s}";

    private final static String EQUALS="%s = #{%s}";

    private final static String LIKE="%s LIKE CONCAT('%s',TRIM(#{%s}),'%s')";

    private final static String IN="%s IN (%s)";

    private final static String MULTIPLE_IN="(%s) IN (%s)";


    /**
     * 根据注解初始化全部表元数据
     * @author jeo_cb
     * @date 2020/1/17
     * @return 表元数据
     */
    public static void init(){

    }

    /**
     * 初始化表元数据
     * @author jeo_cb
     * @date 2020/1/17
     * @param clazz 实体Class
     * @return 表元数据
     */
    private static TableMetadata initTableMetadata(Class<?> clazz){
        TableMetadata tableMetadata=null;
        Table table=clazz.getAnnotation(Table.class);
        if(table!=null){
            tableMetadata=new TableMetadata();
            //获取表名
            String tableName=StringUtils.isNotEmpty(table.value())?table.value():table.name();
            if(StringUtils.isEmpty(tableName)){
                tableName=clazz.getSimpleName();
            }
            tableMetadata.setTableName(tableName.toUpperCase());

            //获取别名
            if(StringUtils.isNotEmpty(table.alias())){
                tableMetadata.setTableName(table.alias());
            }

            //获取列元数据
            List<ColumnMetadata> columnMetadataList=initColumnMetadata(clazz);
            tableMetadata.setColumnMetadataList(columnMetadataList);

            //列和实体属性关系
            Map<String,String> fieldColumnMap = new HashMap<>(columnMetadataList.size());
            Map<String,String> columnFieldMap= new HashMap<>(columnMetadataList.size());
            columnMetadataList.forEach(columnMetadata -> {
                fieldColumnMap.put(columnMetadata.getFieldName(),columnMetadata.getColumnName());
                columnFieldMap.put(columnMetadata.getColumnName(),columnMetadata.getFieldName());
            });
            tableMetadata.setFieldColumnMap(fieldColumnMap);
            tableMetadata.setColumnFieldMap(columnFieldMap);
        }
        return tableMetadata;
    }

    /**
     * 初始化表元数据
     * @author jeo_cb
     * @date 2020/1/17
     * @param clazz 实体Class
     * @return 表元数据
     */
    private static List<ColumnMetadata> initColumnMetadata(Class<?> clazz){
        List<ColumnMetadata> columnMetadataList=new ArrayList<>();
        List<Field> fields= FieldUtils.getAllFieldsList(clazz);
        fields.forEach(field -> {
            Column column=field.getAnnotation(Column.class);
            if(column!=null){
                ColumnMetadata columnMetadata=new ColumnMetadata();
                //获取列名
                String columnName=StringUtils.isNotEmpty(column.value())?column.value():column.name();
                if(StringUtils.isEmpty(columnName)){
                    columnName=field.getName();
                }
                columnMetadata.setColumnName(columnName.toUpperCase());

                //字段名
                columnMetadata.setFieldName(field.getName());

                //获取别名
                if(StringUtils.isNotEmpty(column.alias())){
                    columnMetadata.setAlias(column.alias());
                }
                columnMetadata.setAllowInsert(column.allowInsert());
                columnMetadata.setAllowUpdate(column.allowUpdate());

                //是否主键
                Id id=field.getAnnotation(Id.class);
                if(id!=null){
                    columnMetadata.setPrimaryKey(true);
                }
                columnMetadataList.add(columnMetadata);
            }
        });
        return columnMetadataList;
    }

    /**
     * 获取表元数据
     * @author jeo_cb
     * @date 2020/1/17
     * @param clazz 实体Class
     * @return 表元数据
     */
    private static TableMetadata getTableMetadata(Class<?> clazz){
        TableMetadata tableMetadata= TABLE_CACHE.get(clazz);
        if(tableMetadata==null){
            tableMetadata=initTableMetadata(clazz);
            if(tableMetadata!=null){
                TABLE_CACHE.put(clazz,tableMetadata);
            }
        }
        return tableMetadata;
    }

    /**
     * 根据实体和参数组装查询Sql
     * @author jeo_cb
     * @date 2020/1/17
     * @param clazz 实体class
     * @param distinct 是否去重
     * @param count 是否求和
     * @param primaryKey 是否主键查询
     * @param batchPrimaryKey 是否主键批量查询
     * @param obj 参数
     * @return 查询sql
     */
    static String getSelectSql(Class<?> clazz, boolean distinct, boolean count, boolean primaryKey, boolean batchPrimaryKey, Object obj){
        TableMetadata tableMetadata=getTableMetadata(clazz);
        if(tableMetadata!=null){
            SQL sql=new SQL();

            //Select列
            List<ColumnMetadata> columnMetadataList=tableMetadata.getColumnMetadataList();
            buildSelectColumnSql(columnMetadataList,sql,distinct,count);

            //From表
            buildFromSql(tableMetadata,sql,true);

            //普通字段where条件
            if(obj!=null && !primaryKey && !batchPrimaryKey){
                buildWhereSql(tableMetadata,sql,obj);
            }
            //主键where条件
            buildPrimaryKeyWhereSql(columnMetadataList,sql,primaryKey,batchPrimaryKey,obj);
            return sql.toString();
        }
        return null;
    }

    /**
     * 获取删除Sql
     * @author jeo_cb
     * @date 2020/1/17
     * @param clazz 实体class
     * @param primaryKey 是否主键查询
     * @param batchPrimaryKey 是否主键批量查询
     * @param obj 参数
     * @return 删除sql
     */
    static String getDeleteSql(Class<?> clazz, boolean primaryKey, boolean batchPrimaryKey, Object obj){
        TableMetadata tableMetadata=getTableMetadata(clazz);
        if(tableMetadata!=null){
            SQL sql=new SQL();
            //删除From
            sql.DELETE_FROM(tableMetadata.getTableName());
            //普通字段where条件
            if(obj!=null && !primaryKey && !batchPrimaryKey){
                buildWhereSql(tableMetadata,sql,obj);
            }
            //主键where条件
            List<ColumnMetadata> columnMetadataList=tableMetadata.getColumnMetadataList();
            buildPrimaryKeyWhereSql(columnMetadataList,sql,primaryKey,batchPrimaryKey,obj);
            return sql.toString();
        }
        return null;
    }

    /**
     * 获取插入Sql
     * @author jeo_cb
     * @date 2020/1/17
     * @param  clazz 实体class
     * @return 插入Sql
     **/
    static String getInsertSql(Class<?> clazz,Object obj){
        TableMetadata tableMetadata=getTableMetadata(clazz);
        if(tableMetadata!=null){
            IMetaObjectHandler metaObjectHandler= JfSupport.getContext().getBean("defaultMetaObjectHandlerImpl",IMetaObjectHandler.class);
            metaObjectHandler.handler(obj, MapperMethod.Insert);
            SQL sql=new SQL();
            //插入Table
            sql.INSERT_INTO(tableMetadata.getTableName());
            //插入字段
            List<ColumnMetadata> columnMetadataList=tableMetadata.getColumnMetadataList();
            JSONObject jsonObject= JSON.parseObject(JSON.toJSONString(obj));
            List<String> columns=new ArrayList<>();
            columnMetadataList.forEach(v->{
                Object value=jsonObject.get(v.getFieldName());
                if(v.isAllowInsert() && value!=null){
                    columns.add(v.getColumnName());
                }
            });
            sql.INTO_COLUMNS(columns.toArray(new String[0]));

            //插入值
            List<String> values=new ArrayList<>();
            columnMetadataList.forEach(v->{
                Object value=jsonObject.get(v.getFieldName());
                if(v.isAllowInsert() && value!=null){
                    values.add(String.format(VALUE,v.getFieldName()));
                }
            });
            sql.INTO_VALUES(values.toArray(new String[0]));
            return sql.toString();
        }
        return null;
    }

    /**
     * 构建查询列SQL
     * @author jeo_cb
     * @date 2020/1/17
     * @param columnMetadataList 列元数据
     * @param sql sql对象
     * @param distinct 是否去重
     * @param count 是否求和
     */
    private static void buildSelectColumnSql(List<ColumnMetadata> columnMetadataList,SQL sql,boolean distinct, boolean count){
        if(count){
            sql.SELECT(COUNT);
        }else{
            List<String> columns=new ArrayList<>();
            if(CollectionUtils.isNotEmpty(columnMetadataList)){
                columnMetadataList.forEach(columnMetadata -> {
                    StringBuilder columnSql=new StringBuilder(columnMetadata.getColumnName());
                    columnSql.append(AS);
                    if(StringUtils.isNotEmpty(columnMetadata.getAlias())){
                        columnSql.append(columnMetadata.getAlias());
                    }else{
                        columnSql.append(columnMetadata.getFieldName());
                    }
                    columns.add(columnSql.toString());
                });
            }else{
                columns.add(ALL_COLUMN);
            }
            if(distinct){
                sql.SELECT_DISTINCT(columns.toArray(new String[0]));
            }else{
                sql.SELECT(columns.toArray(new String[0]));
            }
        }
    }

    /**
     * 构建查询列SQL
     * @author jeo_cb
     * @date 2020/1/17
     * @param tableMetadata 表元数据
     * @param sql sql对象
     * @param alias 是否别名
     */
    private static void buildFromSql(TableMetadata tableMetadata,SQL sql,boolean alias){
        StringBuilder tableSql=new StringBuilder(tableMetadata.getTableName());
        if(StringUtils.isNotEmpty(tableMetadata.getAlias()) && alias){
            tableSql.append(AS).append(tableMetadata.getAlias());
        }
        sql.FROM(tableSql.toString());
    }

    /**
     * 构建查询列SQL
     * @author jeo_cb
     * @date 2020/1/17
     * @param tableMetadata 表元数据
     * @param sql sql对象
     * @param obj 参数
     */
    private static void buildWhereSql(TableMetadata tableMetadata,SQL sql,Object obj){
        Map<String,String> fieldColumnMap=tableMetadata.getFieldColumnMap();
        JSONObject jsonObject= JSON.parseObject(JSON.toJSONString(obj));
        jsonObject.forEach((key,value)->{
            String column=fieldColumnMap.get(key);
            if(value!=null && column!=null){
                //数字条件
                if(Number.class.isAssignableFrom(value.getClass()) ||
                        Boolean.class.isAssignableFrom(value.getClass())){
                    sql.WHERE(String.format(EQUALS, column,key));
                }
                //字符条件
                if(CharSequence.class.isAssignableFrom(value.getClass()) && StringUtils.isNotEmpty(value.toString())){
                    sql.WHERE(String.format(LIKE, column,"%",key,"%"));
                }
            }
        });
    }

    /**
     * 构建查询列SQL
     * @author jeo_cb
     * @date 2020/1/17
     * @param columnMetadataList 列元数据
     * @param sql sql对象
     * @param primaryKey 是否主键查询
     * @param batchPrimaryKey 是否主键批量查询
     * @param obj 参数
     */
    private static void buildPrimaryKeyWhereSql(List<ColumnMetadata> columnMetadataList,SQL sql,boolean primaryKey,boolean batchPrimaryKey,Object obj){
        if(primaryKey){
            List<ColumnMetadata> primaryColumns=columnMetadataList.stream().filter(ColumnMetadata::isPrimaryKey).collect(Collectors.toList());
            primaryColumns.forEach(v-> sql.WHERE(String.format(EQUALS, v.getColumnName(),0)));
        }else{
            //主键id批量查询
            if(batchPrimaryKey && obj instanceof Collection){
                Collection<?> collection=(Collection<?>)obj;
                if(!collection.isEmpty()){
                    List<ColumnMetadata> primaryColumns=columnMetadataList.stream().filter(ColumnMetadata::isPrimaryKey).collect(Collectors.toList());
                    if(primaryColumns.size()==1){
                        ColumnMetadata columnMetadata=primaryColumns.get(0);
                        List<String> fields=new ArrayList<>();
                        collection.forEach(f->{
                            fields.add(f.toString());
                        });
                        sql.WHERE(String.format(IN, columnMetadata.getColumnName(),StringUtils.join(fields,",")));

                    }else if(primaryColumns.size()>1){
                        //TODO 待优化
                        List<String> columns=new ArrayList<>();
                        List<String> fields=new ArrayList<>();
                        primaryColumns.forEach(v-> {
                            columns.add(v.getColumnName());
                        });
                        collection.forEach(f->{
                            StringBuilder fieldStr=new StringBuilder();
                            fieldStr.append("(");
                            List<String> fieldList=new ArrayList<>();
                            primaryColumns.forEach(v-> {
                                fieldList.add(String.format(VALUE,v.getFieldName()));
                            });
                            fieldStr.append(StringUtils.join(fieldList,","));
                            fieldStr.append(")");
                            fields.add(fieldStr.toString());
                        });
                        sql.WHERE(String.format(MULTIPLE_IN, StringUtils.join(columns,","),StringUtils.join(fields,",")));
                    }
                }
            }
        }
    }
}
