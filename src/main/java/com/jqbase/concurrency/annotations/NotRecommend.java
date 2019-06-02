package com.jqbase.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不推荐的写法
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface NotRecommend {
    String value() default "";
}
