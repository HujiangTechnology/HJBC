/*
 * JSEventProcessor      2016-07-26
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.js;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hujiang.restvolley.GsonUtils;

import java.lang.reflect.Type;


/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-07-26
 */
public abstract class BaseJSEventProcessor<T extends BaseJSModel> implements Runnable {

    final static protected Gson mGson = GsonUtils.getGson();
    protected String mJSArgs;
    protected String mJSCallbackString;
    protected WebView mWebView;
    protected Handler mUiHandler = new Handler(Looper.getMainLooper());

    public BaseJSEventProcessor(WebView webView, String jsArgs, String jsCallback) {
        if (webView == null) {
            throw new IllegalArgumentException("webView should not be null");
        }
        mWebView = webView;
        mJSArgs = jsArgs;
        mJSCallbackString = jsCallback;
    }

    @Override
    public void run() {
        //parse json to object model
        Type type = new TypeToken<T>(){}.getType();
        final T data = mGson.fromJson(mJSArgs, type);

        mUiHandler.post(new Runnable() {
            @Override
            public void run() {
                onProcessJSEvent(mWebView.getContext(), data);
            }
        });
    }

    @UiThread
    protected abstract void onProcessJSEvent(Context context, T data);
}