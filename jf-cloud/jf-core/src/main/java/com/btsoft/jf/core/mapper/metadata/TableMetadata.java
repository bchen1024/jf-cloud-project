package com.btsoft.jf.core.mapper.metadata;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 表格元数据
 * @author jeo_cb
 * @date 2020/1/17 10:26
 **/
public class TableMetadata implements Serializable {

    private static final long serialVersionUID = -2729816494805991886L;
    private String tableName;
    private String alias;
    private List<ColumnMetadata> columnMetadataList;
    private Map<String,String> fieldColumnMap;
    private Map<String,String> columnFieldMap;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<ColumnMetadata> getColumnMetadataList() {
        return columnMetadataList;
    }

    public void setColumnMetadataList(List<ColumnMetadata> columnMetadataList) {
        this.columnMetadataList = columnMetadataList;
    }

    public Map<String, String> getFieldColumnMap() {
        return fieldColumnMap;
    }

    public void setFieldColumnMap(Map<String, String> fieldColumnMap) {
        this.fieldColumnMap = fieldColumnMap;
    }

    public Map<String, String> getColumnFieldMap() {
        return columnFieldMap;
    }

    public void setColumnFieldMap(Map<String, String> columnFieldMap) {
        this.columnFieldMap = columnFieldMap;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
