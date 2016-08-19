/*
 * DeviceInfoEventProcessor      2016-07-27
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.js.device;

import android.content.Context;
import android.webkit.WebView;

import com.hujiang.js.BaseJSEventProcessor;
import com.hujiang.js.BaseJSConstant;
import com.hujiang.js.annotation.JSMethod;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-07-27
 */
@JSMethod(methodName = BaseJSConstant.DEVICE, argTypes = {"int", "int"})
public class DeviceInfoEventProcessor implements Runnable {

    public DeviceInfoEventProcessor(WebView webView, int jsArgs, int jsCallback) {
//        super(webView, jsArgs, jsCallback);
    }

//    @Override
//    protected void onProcessJSEvent(Context context, DeviceInfoModel data) {
//
//    }

    @Override
    public void run() {

    }
}