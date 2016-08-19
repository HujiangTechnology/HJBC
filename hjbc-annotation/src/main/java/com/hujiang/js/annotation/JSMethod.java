/*
 * HJJSInterface      2016-07-26
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.js.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * js interface annotation.
 * <ul>
 *     <li>jsMethod: js method name, such as 'bi_event'</li>
 *     <li>jsArgTypes: js method argument type sting, such as '{java.lang.String, java.lang.String}'</li>
 *     <li>jsArgs: js method argument name, such as '{jsArgs, jsCallback}'</li>
 * </ul>
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-07-26
 */

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface JSMethod {
    String methodName() default "";
    String[] argTypes() default {"java.lang.String", "java.lang.String"};
}
