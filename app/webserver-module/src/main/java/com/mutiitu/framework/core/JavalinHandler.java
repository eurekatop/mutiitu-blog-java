package com.mutiitu.framework.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;

import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.mutiitu.framework.core.annotations.Role;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.JsonResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.validation.ValidationException;

public class JavalinHandler implements Handler {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private final Method method;
    private final Injector injector;

    public JavalinHandler(Method method, Injector injector) {
        this.method = method;
        this.injector = injector;
    }

    @Override
    public void handle(@NotNull Context ctx) throws Exception, ValidationException {
        // acces management
        var annotationsRole = method.getAnnotationsByType(Role.class);
        logger.info("Mehod is annotated with {}", annotationsRole.toString());

        for (Role role : annotationsRole) {
            var roles = role.value();
            var currentUser = ctx.sessionAttribute("current-user");
            var userRole = ctx.sessionAttribute("user-role");
            var userHash = ctx.sessionAttribute("user-hash");

            // ctx.sessionAttribute("current-user", email);
            // ctx.sessionAttribute("user-role", "admin");
            // ctx.sessionAttribute("user-hash", "hash");

            if (!Arrays.asList(roles).contains(userRole) || currentUser == null || userHash == null) {
                // userRole se encuentra en la lista de roles
                logger.error("No access {}", ctx.url());
                throw new Exception("");
            } else {
                logger.info("Usser {} logged in as {}", currentUser, userRole);
            }
        }

        // get params
        ArrayList<Object> parameterValues = new ArrayList<Object>();
        var parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            var parameterName = parameter.getName();
            var parameterType = parameter.getType();

            var parameterValue = ctx.pathParam(parameterName);

            logger.info("Parameter name:" + parameterName);
            logger.info("Parameter value:" + parameterValue);

            // TODO: refactor
            if (parameterType.equals(String.class)) {
                parameterValues.add(parameterValue);
            } else if (parameterType.equals(int.class)) {
                var value = Integer.parseInt(parameterValue);
                parameterValues.add(value);
            }

        }

        // invoke method
        Class<?> clazz = method.getDeclaringClass();
        JavalinController instance = (JavalinController) injector.getInstance(clazz);
        Object resultInvoke = null;
        Throwable causeExThrowable = null;
        try {
            var params = new ArrayList<Object>();
            params.add("Mortadelo");
            params.add("Filemon");

            instance.ctx = ctx; // ??
            if (parameterValues.size() > 0) {
                resultInvoke = method.invoke(instance, parameterValues.toArray());
            } else {
                resultInvoke = method.invoke(instance);
            }
        } catch (InvocationTargetException ex) {
            logger.error(String.format("Error when invoke method: %s in class %s", method.getName(), clazz.getName()),
                    ex);
            causeExThrowable = ex.getCause();
            if (causeExThrowable instanceof ValidationException) {
                throw (ValidationException) causeExThrowable;
            }
            throw ex;
        } catch (IllegalAccessException ex) {
            logger.error(
                    String.format("!!!! Error when invoke method: %s in class %s", method.getName(), clazz.getName()),
                    ex);
            throw ex;
        }

        // get return type
        // var returnType = method.getReturnType();

        if (resultInvoke instanceof HttpResponse) {
            if (resultInvoke instanceof HtmlResponse) {
                ctx.html(((HtmlResponse) resultInvoke).data);
            }
            if (resultInvoke instanceof StringResponse) {
                ctx.result(((StringResponse) resultInvoke).data);
            }
            if (resultInvoke instanceof JsonResponse) {
                // var data = ((JsonResponse) resultInvoke).toJsonString();
                // ctx.json(data, JsonResponse.class);
                ctx.json(resultInvoke);
            }
        }

    }

}
