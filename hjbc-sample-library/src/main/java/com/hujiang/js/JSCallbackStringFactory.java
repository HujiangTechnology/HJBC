/*
 * JSCallbackStringFactory      2016-07-28
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.js;

import com.hujiang.js.BaseJSCallbackModel;
import com.hujiang.restvolley.GsonUtils;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-07-28
 */
public class JSCallbackStringFactory {

    public static final String HJ_JS_CALLBACK_METHOD_PREFIX = "javascript:HJSDK.callbackFromNative";

    public static <T extends BaseJSCallbackModel> String makeHJJSCallbackMethod(String prefix, String jsMethodName, T callbackData) {
        StringBuilder jsCallbackMethodBuilder = new StringBuilder(prefix);
        jsCallbackMethodBuilder.append("('")
                .append(jsMethodName)
                .append("','")
                .append(GsonUtils.getGson().toJson(callbackData))
                .append("')");

        return jsCallbackMethodBuilder.toString();
    }
}