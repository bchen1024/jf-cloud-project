package com.btsoft.jf.core.mapper.metadata;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 列元数据
 * @author jeo_cb
 * @date 2020/1/17 10:27
 **/
public class ColumnMetadata implements Serializable {
    private static final long serialVersionUID = 7564200391367845324L;

    private String columnName;
    private String alias;
    private String fieldName;
    private boolean primaryKey;
    private boolean allowInsert;
    private boolean allowUpdate;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isAllowInsert() {
        return allowInsert;
    }

    public void setAllowInsert(boolean allowInsert) {
        this.allowInsert = allowInsert;
    }

    public boolean isAllowUpdate() {
        return allowUpdate;
    }

    public void setAllowUpdate(boolean allowUpdate) {
        this.allowUpdate = allowUpdate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
