package cn.haidnor.sqlutil.util;

import cn.haidnor.sqlutil.annotations.*;
import cn.haidnor.sqlutil.dto.Column;
import cn.haidnor.sqlutil.dto.Table;
import cn.haidnor.sqlutil.enums.DataType;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author Haidnor
 */
public class SQLBuilder {

    /**
     * 构建 建表SQL 语句
     *
     * @param dtoClass Table 模型类的 Class对象
     * @return 建表 SQL 语句
     */
    public static String buildCreatTableSQL(Class dtoClass) {
        Table table = new Table();

        table.setName(dtoClass.getSimpleName().toLowerCase());
        TEngine engine = (TEngine) dtoClass.getAnnotation(TEngine.class);
        if (engine != null) {
            table.setEngine(engine.value());
        }
        TCharset charset = (TCharset) dtoClass.getAnnotation(TCharset.class);
        if (charset != null) {
            table.setCharset(charset.value());
        }
        Comments comments = (Comments) dtoClass.getAnnotation(Comments.class);
        if (comments != null) {
            table.setComments(comments.value());
        }

        Field[] fields = dtoClass.getDeclaredFields();
        for (Field field : fields) {
            Column column = new Column();
            // 设置字段名
            column.setName(field.getName());

            Class fieldClass = field.getType();
            // 设置字段数据类型。这里默认设置了 java 数据类型与 mysql 数据类型的对应关系
            if (fieldClass == String.class) {
                // java.lang.String -> VARCHAR
                column.setDataType(DataType.VARCHAR);
                // VARCHAR 数据类型默认长度为255
                column.setLength(255);
            } else if (fieldClass == long.class) {
                // long -> BIGINT
                column.setDataType(DataType.BIGINT);
            } else if (fieldClass == int.class) {
                // int -> INTEGER
                column.setDataType(DataType.INTEGER);
            } else if (fieldClass == short.class) {
                // short -> SMALLINT
                column.setDataType(DataType.SMALLINT);
            } else if (fieldClass == byte.class) {
                // byte -> TINYINT
                column.setDataType(DataType.TINYINT);
            } else if (fieldClass == double.class) {
                // java.lang.Double -> DOUBLE
                column.setDataType(DataType.DOUBLE);
            } else if (fieldClass == float.class) {
                // float -> FLOAT
                column.setDataType(DataType.FLOAT);
            } else if (fieldClass == Boolean.class) {
                // java.lang.Boolean -> TINYINT
                column.setDataType(DataType.TINYINT);
            } else if (fieldClass == byte[].class) {
                // byte[] -> BLOB
                column.setDataType(DataType.BLOB);
            } else if (fieldClass == BigInteger.class) {
                // java.math.BigInteger -> BIGINT
                column.setDataType(DataType.BIGINT);
            } else if (fieldClass == BigDecimal.class) {
                // 	java.math.BigDecimal -> DECIMAL
                column.setDataType(DataType.DECIMAL);
            } else if (fieldClass == Date.class) {
                // java.sql.Date -> DATE
                column.setDataType(DataType.DATE);
            } else if (fieldClass == Time.class) {
                // java.sql.Time -> TIME
                column.setDataType(DataType.TIME);
            } else if (fieldClass == Timestamp.class) {
                // java.sql.Timestamp -> DATETIME
                column.setDataType(DataType.DATETIME);
            } else {
                // 其它类型未定义
                column.setDataType(DataType.Undefined);
            }

            // 设置字段数据类型
            Data data = field.getAnnotation(Data.class);
            if (data != null) {
                column.setDataType(data.value());
                // 设置字段数据长度
                if (data.length() != 0) {
                    column.setLength(data.length());
                }
            }
            // 设置字段数据长度
            Length length = field.getAnnotation(Length.class);
            if (length != null) {
                column.setLength(length.value());
            }
            // 设置字段为主键
            PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
            if (primaryKey != null) {
                column.setPrimaryKey("PRIMARY KEY");
            }
            // 如果标记了 @AutoIncrement 注解,则设置字段为主键
            AutoIncrement autoIncrement = field.getAnnotation(AutoIncrement.class);
            if (autoIncrement != null) {
                column.setPrimaryKey("PRIMARY KEY");
                column.setIsAutoIncrement("AUTO_INCREMENT");
            }
            // 如果字段标记了 @NotNull 注解,则设置字段不为空
            NotNull notNull = field.getAnnotation(NotNull.class);
            if (notNull != null) {
                column.setIsNull("NOT NULL");
            }
            // 设置字段默认值
            Default aDefault = field.getAnnotation(Default.class);
            if (aDefault != null) {
                column.setDefaultValue(aDefault.value());
            }
            // 设置字段注释
            Comments fieldComments = field.getAnnotation(Comments.class);
            if (fieldComments != null) {
                column.setComments(fieldComments.value());
            }

            table.addColumn(column);
        }

        return creatTableSQL(table);
    }

    /**
     * 构建建表 SQL 语句, 分析 Table 模型数据, 拼接 SQL 语句
     *
     * @param table 数据库表模型对象
     * @return 建表 SQL 语句
     */
    private static String creatTableSQL(Table table) {
        StringBuffer sb = new StringBuffer();
        ArrayList<Column> columns = table.getColumns();

        // 表名
        sb.append("CREATE TABLE " + table.getName() + " (" + '\n');
        columns.forEach(column -> {
            sb.append("    ");
            // 字段名
            sb.append(column.getName());
            // 字段数据类型
            sb.append(" " + column.getDataType());
            // 字段数据长度
            if (column.getLength() != 0) {
                sb.append("(" + column.getLength() + ")");
            }
            // 字段主键
            if (column.getPrimaryKey() != null) {
                sb.append(" " + column.getPrimaryKey());
            }
            // 字段自增
            if (column.getIsAutoIncrement() != null) {
                sb.append(" " + column.getIsAutoIncrement());
            }
            // 字段是否为空
            sb.append(" " + column.getIsNull());
            // 字段默认值
            if (column.getDefaultValue() != null) {
                if (column.getDataType() != DataType.VARCHAR) {
                    sb.append(" " + "default " + column.getDefaultValue());
                } else {
                    sb.append(" " + "default '" + column.getDefaultValue() + "'");
                }
            }
            // 字段注解
            if (column.getComments() != null) {
                sb.append(" " + "COMMENT '" + column.getComments() + "'," + '\n');
            } else {
                sb.append("," + '\n');
            }
        });

        sb.deleteCharAt(sb.length() - 2);

        // 表引擎
        sb.append(")ENGINE=" + table.getEngine());
        // 表字符集
        sb.append(" " + "DEFAULT CHARSET=" + table.getCharset());
        // 表注释
        if (table.getComments() != "") {
            sb.append(" " + "COMMENT '" + table.getComments() + "'");
        }
        sb.append(";");

        return sb.toString();
    }

}
