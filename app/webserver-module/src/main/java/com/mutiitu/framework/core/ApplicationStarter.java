package com.mutiitu.framework.core;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.LoggerFactory;
import io.javalin.Javalin;
import com.google.inject.Inject;
import com.google.inject.Injector;

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

        // TODO: refactor
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
            // /home2/:param1/:param2


            if ( route.contains(":" )){
                var parts = path.Value().split("/");
                var _route = "";
                int param = 0;
                for (String part : parts) {
                    if (part.startsWith(":")) {
                        //_route = _route + "/<" + part.substring(1) + ">";
                        _route = _route + "/<" + "arg" + param + ">";
                        param++;
                    } else {
                        if (part.isEmpty()) {
                            continue;
                        }
                        _route = _route + "/" + part;
                    }
                }
                route = _route;
            }
            else {
                // get params
                var parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    route = route + "/<" + parameter.getName() + ">";
                }
            }


            var handler = new JavalinHandler(method, injector);

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
        // Command-line args handling
        for (String arg : args) {
            logger.info(arg);
        }


        // Search for controllers and register routes
        searchForControllerAnnotatedClasses();

        // Setup Guice filter (handled via the GuicePlugin)
        javalin.before("/*", ctx -> {
            logger.info("### BEFORE HANDLER " + Thread.currentThread());

            // TODO: refactor
            // var a = new com.google.inject.servlet.GuiceFilter();
            // javalin.javalinServlet().getServletContext().addFilter("guiceFilter",
            // a.getClass());

        });

        // Bind routes via your custom router
        router.bind();

        // Start Javalin on port 8080
        javalin.start(8080);

        // Global exception handlers
        javalin.exception(io.javalin.validation.ValidationException.class, (e, ctx) -> {
            ctx.status(400).json(e);
        });

        javalin.exception(Exception.class, (e, ctx) -> {
            ctx.result(e.getMessage()).status(410);
        });

        javalin.exception(InvocationTargetException.class, (e, ctx) -> {
            ctx.result(e.getCause().getMessage()).status(500);
        });
      
    }
}
