/*
 * JSBridgeProcessor      2016-07-27
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.js.compiler;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import com.hujiang.js.annotation.JSBridge;
import com.hujiang.js.annotation.JSBridgeGroup;
import com.hujiang.js.annotation.JSMethod;
import com.squareup.javapoet.*;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-07-27
 */
@AutoService(Processor.class)
public class HJBCProcessor extends AbstractProcessor {

    private static final String DEF_JS_BRIDGE_PACKAGE = "com.hujiang.js";
    private static final String DEF_JS_BRIDGE_TYPE = "JSEvent";

    public static ClassName WEBVIEW = ClassName.get("android.webkit", "WebView");
    public static ClassName JAVASCRIPT_INTERFACE = ClassName.get("android.webkit", "JavascriptInterface");
    public static String FIELD_WEBVIEW = "mWebView";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return ImmutableSet.of(JSMethod.class.getCanonicalName()
                , JSBridge.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        genJavaFile(getTypeSpecs(roundEnv, getMethodSpecs(roundEnv)));
        return true;
    }

    private void genJavaFile(Map<TypeBinder, TypeSpec> typeSpecs) {
        if (!typeSpecs.isEmpty()) {
            for (Map.Entry<TypeBinder, TypeSpec> item : typeSpecs.entrySet()) {
                JavaFile javaFile = JavaFile.builder(item.getKey().packageName, item.getValue()).build();
                try {
                    javaFile.writeTo(processingEnv.getFiler());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Map<TypeBinder, TypeSpec> getTypeSpecs(RoundEnvironment roundEnv, Map<String, MethodSpec> methodSpecs) {
        Map<TypeBinder, TypeSpec> typeSpecs = new LinkedHashMap<>();
        Set<? extends Element> jsBridgeElements = roundEnv.getElementsAnnotatedWith(JSBridge.class);
        Set<? extends Element> jsBridgeGroupElements = roundEnv.getElementsAnnotatedWith(JSBridgeGroup.class);

        if (!jsBridgeElements.isEmpty()) {
            for (Element element : jsBridgeElements) {
                parseJSBridge(typeSpecs, methodSpecs, element, element.getAnnotation(JSBridge.class));
            }
        }

        if (!jsBridgeGroupElements.isEmpty()) {
            for (Element element : jsBridgeGroupElements) {
                JSBridgeGroup jsBridgeGroup = element.getAnnotation(JSBridgeGroup.class);
                JSBridge[] bridges = jsBridgeGroup.jsBridges();
                if (bridges != null && bridges.length > 0) {
                    for (JSBridge jsBridge : bridges) {
                        parseJSBridge(typeSpecs, methodSpecs, element, jsBridge);
                    }
                }
            }
        }

        if (typeSpecs.isEmpty() && !methodSpecs.isEmpty()) {
            parseJSBridge(typeSpecs, methodSpecs, new JSBridge() {

                @Override
                public Class<? extends Annotation> annotationType() {
                    return JSBridge.class;
                }

                @Override
                public String name() {
                    return DEF_JS_BRIDGE_TYPE;
                }

                @Override
                public String parent() {
                    return null;
                }

                @Override
                public String[] jsMethods() {
                    return null;
                }
            }, DEF_JS_BRIDGE_PACKAGE, DEF_JS_BRIDGE_TYPE);
        }

        return typeSpecs;
    }

    private Map<String, MethodSpec> getMethodSpecs(RoundEnvironment roundEnv) {
        Set<? extends Element> methodElements = roundEnv.getElementsAnnotatedWith(JSMethod.class);
        Map<String, MethodSpec> methodSpecs = new LinkedHashMap<>();
        if (!methodElements.isEmpty()) {
            for (Element element : methodElements) {
                if (element.getKind() != ElementKind.CLASS) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "only support class");
                }

                JSMethod jsMethod = element.getAnnotation(JSMethod.class);

                MethodSpec.Builder jsMethodBuilder = MethodSpec.methodBuilder(jsMethod.methodName())
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(JAVASCRIPT_INTERFACE)
                        .returns(void.class);

                int size = jsMethod.argTypes().length;
                String argName = "arg%s";
                StringBuilder statementBuilder = new StringBuilder("new $T(mWebView,");
                Object[] args = new Object[size + 1];
                args[0] = element.asType();
                for (int i = 0; i < size; i++) {
                    //add method parameter
                    TypeName typeName = null;
                    String type = jsMethod.argTypes()[i];
                    int lastDotIndex = type.lastIndexOf('.');
                    if (lastDotIndex == -1) {
                        typeName = BasicTypeUtils.getBasicTypeName(type);
                    } else {
                        try {
                            typeName = TypeName.get(Class.forName(type));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                    String parameterName = String.format(argName, i);
                    args[i + 1] = parameterName;
                    jsMethodBuilder.addParameter(typeName, parameterName);

                    //build method statement
                    statementBuilder.append(" $L");
                    if (i < (size - 1)) {
                        statementBuilder.append(",");
                    }
                }

                statementBuilder.append(").run()");

                jsMethodBuilder.addStatement(statementBuilder.toString(), args);

                methodSpecs.put(jsMethod.methodName(), jsMethodBuilder.build());
            }
        }

        return methodSpecs;
    }

    private void parseJSBridge(Map<TypeBinder, TypeSpec> typeSpecMap, Map<String, MethodSpec> methodSpecs, JSBridge jsBridge, String packageName, String
            typeName) {
        //field
        FieldSpec webViewFieldSpec = FieldSpec.builder(WEBVIEW, FIELD_WEBVIEW, Modifier.PROTECTED).build();

        //parent
        ClassName superClass = null;
        String parent = jsBridge.parent();
        if (parent != null && parent.length() > 0) {
            int lastIndex = parent.lastIndexOf('.');
            superClass = ClassName.get(parent.substring(0, lastIndex), parent.substring(lastIndex + 1));
        }

        //construct
        MethodSpec.Builder constructMethodSpecBuilder = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(WEBVIEW, "webView");

        if (superClass != null) {
            constructMethodSpecBuilder.addStatement("super($N)", "webView");
        }
        constructMethodSpecBuilder.addStatement("this.$N = $N", FIELD_WEBVIEW, "webView");

        //type
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(typeName)
                .addModifiers(Modifier.PUBLIC)
                .addField(webViewFieldSpec)
                .addMethod(constructMethodSpecBuilder.build());

        //add super class
        if (superClass != null) {
            typeSpecBuilder.superclass(superClass);
        }

        //add js method
        if (!methodSpecs.isEmpty()) {
            String[] methodNames = jsBridge.jsMethods();
            if (methodNames == null || methodNames.length == 0) {
                for (Map.Entry<String, MethodSpec> item : methodSpecs.entrySet()) {
                    typeSpecBuilder.addMethod(item.getValue());
                }
            } else {
                for (String name : methodNames) {
                    MethodSpec method = methodSpecs.get(name);
                    if (method != null) {
                        typeSpecBuilder.addMethod(method);
                    }
                }
            }
        }

        typeSpecMap.put(new TypeBinder(packageName, typeName), typeSpecBuilder.build());

    }

    private void parseJSBridge(Map<TypeBinder, TypeSpec> typeSpecMap, Map<String, MethodSpec> methodSpecs, Element element, JSBridge jsBridge) {
        int lastDotIndex = jsBridge.name() == null ? -1 : jsBridge.name().lastIndexOf('.');
        String packageName;
        String typeName;
        if (lastDotIndex == -1) {
            typeName = jsBridge.name() == null ? DEF_JS_BRIDGE_TYPE : jsBridge.name();
            String elementFullName = element.asType().toString();
            packageName = elementFullName.substring(0, elementFullName.lastIndexOf('.'));
        } else {
            typeName = jsBridge.name().substring(lastDotIndex + 1);
            packageName = jsBridge.name().substring(0, lastDotIndex);
        }

        parseJSBridge(typeSpecMap, methodSpecs, jsBridge, packageName, typeName);
    }
}