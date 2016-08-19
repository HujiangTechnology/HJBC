/*
 * WebView      2016-07-26
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.browser;

import android.content.Context;
import android.util.AttributeSet;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-07-26
 */
public class HJWebView extends android.webkit.WebView {

    public HJWebView(Context context) {
        super(context);
    }

    public HJWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HJWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}