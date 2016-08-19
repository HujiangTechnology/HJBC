/*
 * JSAbility		2016-08-12
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.js;

import com.hujiang.js.annotation.JSBridge;
import com.hujiang.js.annotation.JSBridgeGroup;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-08-12
 */
@JSBridgeGroup(jsBridges = {
    @JSBridge(name = "JSEvent1", jsMethods = {BaseJSConstant.BI, BaseJSConstant.TOAST, BaseJSConstant.DEVICE}),
    @JSBridge(name = "JSEvent2", jsMethods = {BaseJSConstant.BI, BaseJSConstant.TOAST})})
@JSBridge(name = "JSEvent")
public class BaseJSConstant {

    public static final String BI = "bi_onEvent";
    public static final String TOAST = "ui_toast";
    public static final String DEVICE = "device_info";
}
