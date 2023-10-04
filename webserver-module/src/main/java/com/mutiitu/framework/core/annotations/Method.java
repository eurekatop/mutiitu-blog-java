/**
 * The {@code Method} annotation is used to mark a method with a specific value in the context of an application.
 * It allows you to associate a custom value with a method.
 */
package com.mutiitu.framework.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Method {
    //TODO: ENUM POST GET
    String Value();
}