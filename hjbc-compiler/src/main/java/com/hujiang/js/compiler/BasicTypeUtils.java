/*
 * BasicTypeUtils		2016-08-19
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.js.compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-08-19
 */
public class BasicTypeUtils {
    public static final String OBJECT = "Object";
    public static final String JAVA_LANG = "java.lang";

    public static TypeName getBasicTypeName(String typeString) {
        if (TypeName.BOOLEAN.toString().equals(typeString)) {
            return TypeName.BOOLEAN;
        } else if (TypeName.BYTE.toString().equals(typeString)) {
            return TypeName.BYTE;
        } else if (TypeName.VOID.toString().equals(typeString)) {
            return TypeName.VOID;
        } else if (TypeName.SHORT.toString().equals(typeString)) {
            return TypeName.SHORT;
        } else if (TypeName.INT.toString().equals(typeString)) {
            return TypeName.INT;
        } else if (TypeName.LONG.toString().equals(typeString)) {
            return TypeName.LONG;
        } else if (TypeName.CHAR.toString().equals(typeString)) {
            return TypeName.CHAR;
        } else if (TypeName.FLOAT.toString().equals(typeString)) {
            return TypeName.FLOAT;
        } else if (TypeName.DOUBLE.toString().equals(typeString)) {
            return TypeName.DOUBLE;
        } else if (OBJECT.equals(typeString)) {
            return TypeName.OBJECT;
        } else {
            int dotIndex = typeString == null ? -1 : typeString.indexOf('.');

            if (dotIndex == -1) {
                String className = new StringBuilder(JAVA_LANG).append('.').append(typeString).toString();
                try {
                    return TypeName.get(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    return TypeName.get(Class.forName(typeString));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }

        return null;
    }
}
