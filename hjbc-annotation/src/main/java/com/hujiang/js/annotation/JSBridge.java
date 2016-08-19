/*
 * HJJSInterface      2016-07-26
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 *
 */
package com.hujiang.js.annotation;

import javax.lang.model.type.TypeMirror;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * For javascript interface file define.
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-08-18
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface JSBridge {
    String name() default "JSEvent";
    String parent() default "";
    String[] jsMethods() default {};
}