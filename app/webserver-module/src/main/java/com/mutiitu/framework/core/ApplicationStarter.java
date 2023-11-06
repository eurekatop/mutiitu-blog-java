package com.mutiitu.framework.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.google.inject.Injector;
import io.javalin.Javalin;
import io.javalin.http.ContentType;
import io.javalin.rendering.template.JavalinThymeleaf;
import io.javalin.validation.ValidationError;
import io.javalin.validation.ValidationException;

import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Path;

public class ApplicationStarter {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private final Javalin javalin;
    private final Router router;
    private final Injector injector;

    @Inject
    public ApplicationStarter(Javalin javalin, Router router, Injector injector) {
        this.javalin = javalin;
        this.router = router;
        this.injector = injector;
    }

    public void searchForControllerAnnotatedClasses() {
        var reflections = new Reflections(new ConfigurationBuilder()
                .forPackage("mutiitu.blog.controllers") // TODO: refactor
                .setScanners(
                        Scanners.TypesAnnotated,
                        Scanners.MethodsAnnotated));

        var controllers = reflections.getTypesAnnotatedWith(Controller.class);
        var paths = reflections.getMethodsAnnotatedWith(Path.class);

        // System.out.println("-- Controllers: -----------------------------");
        // for (Class<?> c : controllers) {
        // System.out.println(c);
        // var instance = injector.getInstance(c);
        // if ( "HelloWorldRouter".equals(c.getSimpleName()) ){
        // HelloWorldRouter a = (HelloWorldRouter) instance;
        // a.aa();
        // }
        // }

        System.out.println("-- Methods: -----------------------------");
        for (Method method : paths) {
            var isHttpVerb = method.isAnnotationPresent(com.mutiitu.framework.core.annotations.Method.class);
            String httpVerb; // TODO: refactor enum
            var path = method.getAnnotation(Path.class);
            var route = path.Value();

            var handler = new JavalinHandler(method, injector);

            // get params
            var parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                route = route + "/<" + parameter.getName() + ">";
            }

            logger.info("Route added value:" + route);

            if (isHttpVerb) {
                var _httpVerb = method.getAnnotation(com.mutiitu.framework.core.annotations.Method.class);
                httpVerb = _httpVerb.Value();
            } else {
                httpVerb = "GET";
            }

            switch (httpVerb) {
                case "GET":
                    javalin.get(route, handler);
                    break;
                case "POST":
                    javalin.post(route, handler);
                    break;
            }

            // javalin.get("hola/:id", ctx -> {
            // ctx.req().
            // System.out.println(ctx.);
            // });
        }

    }

    public void run(String... args) {
        // command line
        for (String arg : args) {
            logger.info(arg);
        }

        // TODO: deprecated
        JavalinThymeleaf.init();

        // search controllers
        searchForControllerAnnotatedClasses();

        // try to guice request scoped
        javalin.before("/*", ctx -> {

            logger.info("BEFOR HANDLER " + Thread.currentThread());

            // var a = new com.google.inject.servlet.GuiceFilter();
            // javalin.javalinServlet().getServletContext().addFilter("guiceFilter",
            // a.getClass());

        });

        router.bind();
        javalin.start(8080);

        javalin.exception(ValidationException.class, (e, ctx) -> {
            ctx.status(400);
            //ctx.json(e.getErrors());
            ctx.json(e);
            ctx.contentType(ContentType.APPLICATION_JSON);
        });

        javalin.exception(Exception.class, (e, ctx) -> {
            ctx.result(e.getMessage());
            ctx.status(410);
        });

        javalin.exception(InvocationTargetException.class, (e, ctx) -> {
            ctx.result(e.getCause().getMessage());
            ctx.status(500);
        });

        var a = new com.google.inject.servlet.GuiceFilter();
        // a.doFilter(ctx.req(), ctx.res(), null);

        try {
            Thread.sleep(2);
            javalin.javalinServlet().getServletContext().addFilter("guiceFilter",
                    a.getClass());
        } catch (Exception ex) {
            logger.error(null, ex);
        }

    }
}