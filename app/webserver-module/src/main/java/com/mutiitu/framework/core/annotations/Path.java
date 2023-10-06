/**
 * The {@code Path} annotation is used to define a path route for a method in the context of an application.
 * It allows you to specify a custom path value that is associated with the method.
 *
 */
package com.mutiitu.framework.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Path {
    // Puedes agregar elementos (atributos) a tu anotación si es necesario
    String Value();
}