package cn.haidnor.sqlutil.enums;

/**
 * @author Haidnor
 */
public enum DataType {
    // 串数据类型
    CHAR,
    ENUM,
    LONGTEXT,
    MEDIUMTEXT,
    SET,
    TEXT,
    TINYTEXT,
    VARCHAR,

    // 数值数据类型
    BIT,
    BIGINT,
    DOUBLE,
    FLOAT,
    INT,
    MEDIUMINT,
    REAL,
    SMALLINT,
    TINYINT,
    INTEGER,
    DECIMAL,

    // 时间和日期数据类型
    DATE,
    DATETIME,
    TIMESTAMP,
    TIME,
    YEAR,

    // 二进制数据类型
    BLOB,
    MEDIUMBLOB,
    LONGBLOB,
    TINYBLOB,

    // 未定义
    Undefined;
}
