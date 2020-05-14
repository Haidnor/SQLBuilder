package cn.haidnor.sqlutil.annotations;

import cn.haidnor.sqlutil.enums.Charset;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Haidnor
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TCharset {
    Charset value();
}
