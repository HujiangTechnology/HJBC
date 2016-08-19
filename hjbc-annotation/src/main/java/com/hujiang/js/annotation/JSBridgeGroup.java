/*
 * JSBridges		2016-08-19
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.js.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-08-19
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface JSBridgeGroup {
    JSBridge[] jsBridges() default {};
}
