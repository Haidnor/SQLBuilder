package cn.haidnor.sqlutil.dto;

import cn.haidnor.sqlutil.enums.DataType;

/**
 * @author Haidnor
 */
public class Column {
    /**
     * 字段名称
     */
    private String name = "";

    /**
     * 字段数据类型
     */
    private DataType dataType = null;

    /**
     * 字段长度
     */
    private int length = 0;

    /**
     * 字段注释
     */
    private String comments = "";

    /**
     * 是否为主键
     */
    private String primaryKey = "";

    /**
     * 是否为自增量
     */
    private String isAutoIncrement = "";

    /**
     * 是否不为空
     */
    private String isNull = "DEFAULT NULL";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getIsAutoIncrement() {
        return isAutoIncrement;
    }

    public void setIsAutoIncrement(String isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }
}