/*
 * TypeBinder		2016-08-19
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.js.compiler;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-08-19
 */
public class TypeBinder {
    public String packageName;
    public String typeName;

    public TypeBinder(String packageName, String typeName) {
        this.packageName = packageName;
        this.typeName = typeName;
    }
}
