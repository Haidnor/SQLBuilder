package cn.haidnor.sqlBuilder.annotations;

import cn.haidnor.sqlBuilder.enums.DataType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Haidnor
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Data {
    DataType value();
    int length();
}
