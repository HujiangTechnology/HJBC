/*
 * DemoWebViewAcitvity		2016-08-18
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.js.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-08-18
 */
public class DemoWebViewActivity extends Activity {

    WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webview_layout);

        mWebView = (WebView)findViewById(R.id._webview);

        mWebView.getSettings().setJavaScriptEnabled(true);
    }
}
