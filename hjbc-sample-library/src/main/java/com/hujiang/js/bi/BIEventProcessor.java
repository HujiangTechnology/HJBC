/*
 * BIJSEventProcessor      2016-07-26
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.js.bi;

import android.content.Context;
import android.support.annotation.UiThread;
import android.webkit.WebView;

import com.hujiang.js.BaseJSConstant;
import com.hujiang.js.JSCallbackStringFactory;
import com.hujiang.js.BaseJSEventProcessor;
import com.hujiang.js.annotation.JSMethod;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-07-26
 */
@JSMethod(methodName = BaseJSConstant.BI, argTypes = {"String", "String"})
public class BIEventProcessor extends BaseJSEventProcessor<BIModel> {

    public BIEventProcessor(WebView webView, String jsArgs, String jsCallback) {
        super(webView, jsArgs, jsCallback);
    }

    @Override
    @UiThread
    protected void onProcessJSEvent(Context context, BIModel data) {

        //process js callback
        BICallbackModel biCallbackModel = new BICallbackModel();
        mWebView.loadUrl(JSCallbackStringFactory.makeHJJSCallbackMethod(JSCallbackStringFactory.HJ_JS_CALLBACK_METHOD_PREFIX, mJSCallbackString, biCallbackModel));
    }
}