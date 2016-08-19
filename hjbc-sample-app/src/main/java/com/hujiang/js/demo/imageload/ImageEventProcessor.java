/*
 * ImageEventProceesor      2016-07-27
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.js.demo.imageload;

import android.content.Context;
import android.webkit.WebView;

import com.hujiang.js.BaseJSEventProcessor;
import com.hujiang.js.annotation.JSMethod;
import com.hujiang.js.demo.JSConstant;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-07-27
 */
@JSMethod(methodName = JSConstant.IMAGE_LOADER)
public class ImageEventProcessor extends BaseJSEventProcessor<ImageJSModel> {
    public ImageEventProcessor(WebView webView, String jsArgs, String jsCallback) {
        super(webView, jsArgs, jsCallback);
    }

    @Override
    protected void onProcessJSEvent(Context context, ImageJSModel data) {
        //process js event


        //process callback
    }
}