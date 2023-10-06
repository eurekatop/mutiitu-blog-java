package com.mutiitu.framework.core.di;

import java.nio.file.Path;
import java.util.ArrayList;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

//import mutiitu.blog.components.HeaderService;
//import mutiitu.blog.components.HeaderServiceImpl;
//import mutiitu.blog.routes.BlogRouter;
import com.mutiitu.framework.core.ApplicationStarter;
import com.mutiitu.framework.core.AutoShutdownPlugin;
//import com.mutiitu.framework.core.Router;

public class CoreModule extends AbstractModule {
    @Override
    protected void configure() {
        //bind(HeaderService.class).to(HeaderServiceImpl.class);


        //binder().requireExplicitBindings();
        //install(new JavalinModule());
        //install(new MessageModule());

        bind(Javalin.class).toInstance(Javalin.create(
            config -> {

            // TODO: Use only in development mode, restarts javalin on any change hot reload
            var rootPath = Path.of(System.getProperty("user.dir")).getParent();
            var paths = new ArrayList<Path>();
            paths.add( Path.of(rootPath + "/main-module/src"));
            paths.add( Path.of(rootPath + "/persistence-module/src"));
            paths.add( Path.of(rootPath + "/webserver-module/src"));

            var autoShutdownPlugin = new AutoShutdownPlugin(paths);
    
            config.plugins.register(autoShutdownPlugin);

            // static files
            // TODO: APPLICATION NAME mutiitu, environment
            config.staticFiles.add("/tmp", Location.EXTERNAL);
            String executionPath = System.getProperty("user.dir");
            config.staticFiles.add(String.format("%s/build/resources/public", executionPath), Location.EXTERNAL);
            }
        ));

        bind(ApplicationStarter.class).in(Scopes.SINGLETON);

    }
}   