/**
 * This {@code Controller} annotation is used to mark a class as a controller in the context of an application.
 * Controllers are responsible for handling incoming requests and performing specific actions.
 *
 * You can add custom elements (attributes) to this annotation if necessary.
 */
package com.mutiitu.framework.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {
}