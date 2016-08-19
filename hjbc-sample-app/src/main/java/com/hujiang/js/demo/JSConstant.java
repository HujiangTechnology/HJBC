/*
 * JSConstant		2016-08-17
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.js.demo;

import com.hujiang.js.annotation.JSBridge;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-08-17
 */
@JSBridge(name = "DemoJSEvent", parent = "com.hujiang.js.JSEvent")
public class JSConstant {

    public static final String LOG = "log_event";
    public static final String IMAGE_LOADER = "image_event";
    public static final String WEB_API = "webapi_event";
}
