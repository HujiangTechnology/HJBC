/*
 * WebApiProcessor		2016-08-16
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.js.demo.webapi;

import android.content.Context;
import android.webkit.WebView;
import com.hujiang.js.BaseJSEventProcessor;
import com.hujiang.js.BaseJSModel;
import com.hujiang.js.annotation.JSMethod;
import com.hujiang.js.demo.JSConstant;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-08-16
 */
@JSMethod(methodName = JSConstant.WEB_API)
public class WebApiProcessor extends BaseJSEventProcessor<WebApiJSModel> {
    public WebApiProcessor(WebView webView, String jsArgs, String jsCallback) {
        super(webView, jsArgs, jsCallback);
    }

    @Override
    protected void onProcessJSEvent(Context context, WebApiJSModel data) {

    }
}
