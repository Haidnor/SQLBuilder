# 简介
SQLBuilder 是一个用于生成 MySql 数据库建表 SQL 语句的工具包。开发者只需创建数据表模型类，对其使用提供的注解进行标记，然后调用相关的方法，运行程序后即可在控制台上输出该类的建表语句。

# 使用案例
```java
import cn.haidnor.sqlBuilder.annotations.*;
import cn.haidnor.sqlBuilder.enums.*;
import cn.haidnor.sqlBuilder.util.SQLBuilder;
import java.sql.Date;

@TEngine(Engine.MyISAM)
@TCharset(Charset.utf8)
@Comments("用户信息表")
public class User {

    @PrimaryKey
    @NotNull
    @Comments("用户id")
    long user_id;

    @NotNull
    @Length(20)
    @Comments("用户姓名")
    String user_name;

    @Data(value = DataType.CHAR,length = 6)
    @NotNull
    @Default("123456")
    @Comments("用户密码")
    String user_password;

    @Default("1")
    @Comments("用户类型,1:普通用户,2:管理员")
    byte user_role;

    @Comments("上一次用户登录的时间")
    Date user_last_login_time;

    public static void main(String[] args) {
        String creatTableSQL = SQLBuilder.buildCreatTableSQL(User.class);
        System.out.println(creatTableSQL);
    }

}
```
运行以上代码后,控制台会输出以下内容
```
CREATE TABLE user (
    user_id BIGINT PRIMARY KEY NOT NULL COMMENT '用户id',
    user_name VARCHAR(20) NOT NULL COMMENT '用户姓名',
    user_password CHAR(11) NOT NULL default 123456 COMMENT '用户密码',
    user_role TINYINT DEFAULT NULL default 1 COMMENT '用户类型,1:普通用户,2:管理员',
    user_last_login_time DATE DEFAULT NULL COMMENT '上一次用户登录的时间'
)ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT '用户信息表';
```
## 案例讲解
User 类是一个对应数据库表的 DTO 类, @TEngine 设置表使用的引擎, @TCharset 设置表的字符集, @Comments 设置了表的注释。  

在表内的字段上，@Data 设置该字段的数据类型与数据长度，@PrimaryKey 标记该字段为主键，@NotNull 标记该字段不可为空，@Default 设置字段的默认值，@Comments 设置字段的注释。  

SQLBuilder 设定了一些枚举来限制可以传入以上注解内的参数，Charset 枚举规定了允许使用的字符集，Engine 枚举规定了允许使用的数据库引擎，DataType 枚举规定了允许使用的 MYSQL 数据类型。   

字段的数据类型，SQLBuilder中提供了一部分默认的对应解决方案，例如在 User 类中声明 String 类型的数据会被默认解析为 Mysql 数据库中的 VARCHAR 数据类型。当然也可以使用 @Data 注解来自定义字段的数据类型。更多详细的说明请参考下文的 “SQLBuilder 中 Java 数据类型与默认生成的 MYSQL 数据类型的对应关系表”。   

最后使用``SQLBuilder.buildCreatTableSQL(Class dtoClass)``方法，将 User 类的 Class 对象作为参数传入，调用该方法即可生成建表的 SQL 语句。
# 注解说明
|注解名称|传入参数|标记范围|说明|
|--------|------|--------|----|
|TEngine|enum Engine|类|为表设置引擎，不使用该注解情况下默认为 InnoDB|
|TCharset|enum Charset|类|为表设置字符集，不使用该注解情况下默认为 utf8|
| | | | |
|Data|enum DataType，String length|字段|设置字段的数据类型与数据长度|
|Length|int length|字段|设置字段的数据长度|
|PrimaryKey| |字段|标记字段的为主键|
|AutoIncrement| |字段|标记字段的为自增|
|NotNull| |字段|设置字段不允许为空，不适用该注解的情况下默认为 DEFAULT NULL|
|Default|String value|字段|设置字段的默认值|
| | | | |
|Comments | |类、字段|设置注释 |

# 枚举说明

## Charset 
用于设置表的字符集   

| 值 | 
|---|
|utf8|
|gbk|
|latin1|
## Engine
设置表使用的引擎 

| 值 | 
|---|
|InnoDB|
|MYMORY|
|MyISAM|
## DataType
设置 MYSQL 数据库中字段的数据类型

| 串数据类型 | 
|---|
|CHAR|
|ENUM|
|LONGTEXT|
|MEDIUMTEXT|
|SET|
|TEXT|
|TINYTEXT|
|VARCHAR|
|数值数据类型|
|BIT|
|BIGINT|
|DOUBLE|
|FLOAT|
|INT|
|MEDIUMINT|
|REAL|
|SMALLINT|
|TINYINT|
|INTEGER|
|DECIMAL|
| |
|时间和日期数据类型|
|DATE|
|DATETIME|
|TIMESTAMP|
|TIME|
|YEAR|
| |
|二进制数据类型|
|BLOB|
|MEDIUMBLOB|
|LONGBLOB|
|TINYBLOB|

此外还有一个未定义的类型

||
|---|
|未定义|
|Undefined|


# SQLBuilder 中 Java 数据类型与默认生成的 MYSQL 数据类型的对应关系表
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
|其它类型| | | |默认解析为 Undefined ，需要手动修改|
