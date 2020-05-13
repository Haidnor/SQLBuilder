# 简介
SQLBuilder 是一个用于生成 MySql 数据库建表 SQL 语句的工具包。开发者只需创建数据表模型类，对其使用提供的注解进行标记，然后调用相关的方法，运行程序后即可在控制台上输出该类的建表语句。

# 使用案例

# 注解说明

#  Java 数据类型与默认生成的 MYSQL 数据类型的对应关系表
| Java 数据类型 | Mysql数据类型 | 字节数 | 储存范围 |备注| 
|---|---|---|---|---|
| |
|整数类型|
|byte|TINYINT|1|
|short|SMALLINT|2|
|int|INTEGER|4|
|long|BIGINT|8|
| |
|浮点数类型|
|float|FLOAT|4|
|double|DOUBLE|8|
| |
|大数值类型|
|java.math.BigDecimal|DECIMAL|M+2|
| |
|时间类型|
|java.sql.Time|TIME|3|
|java.sql.Date|DATE|4|
|java.sql.Timestamp|DATETIME|8|
| |
|布尔类型|
|java.lang.Boolean|TINYINT|1| |0 为 false , 1 为 true|
| |
|二进制类型|
|byte[]|BLOB||65535|
| |
|字符串类型|
|Char|CHAR|2| | | 
|String|VARCHAR|65535| |默认长度为 255|
| |
|其它类型| | | |默认解析为 Undefined 需要手动修改|
