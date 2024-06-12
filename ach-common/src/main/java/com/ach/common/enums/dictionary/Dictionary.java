package com.ach.common.enums.dictionary;

import java.lang.annotation.*;

/**
 * 字典类型注解
 *
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dictionary {


    /**
     * 字典类型名称
     */
    String name() default "";

}
