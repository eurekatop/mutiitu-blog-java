package com.mutiitu.framework.core.di;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import io.javalin.Javalin;
import io.javalin.config.PrivateConfig;
import io.javalin.config.StaticFilesConfig;
import io.javalin.http.ContentType;
import io.javalin.http.staticfiles.Location;
import io.javalin.json.JsonMapper;

import com.mutiitu.framework.core.ApplicationStarter;
import com.mutiitu.framework.core.AutoShutdownPlugin;
import com.mutiitu.framework.core.Router;
import com.mutiitu.framework.core.ui.RouterImpl;

public class CoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Router.class).to(RouterImpl.class);

        // TODO : extract function to another place
        bind(Javalin.class).toInstance(Javalin.create(
                config -> {

                    // TODO: Use only in development mode, restarts javalin on any change hot reload
                    var rootPath = Path.of(System.getProperty("user.dir")).getParent();
                    var paths = new ArrayList<Path>();
                    paths.add(Path.of(rootPath + "/main-module/src"));
                    paths.add(Path.of(rootPath + "/persistence-module/src"));
                    paths.add(Path.of(rootPath + "/webserver-module/src"));

                    var autoShutdownPlugin = new AutoShutdownPlugin(paths);

                    config.plugins.register(autoShutdownPlugin);

                    // static files
                    // TODO: APPLICATION NAME mutiitu, environment
                    config.staticFiles.add("/tmp", Location.EXTERNAL);
                    String executionPath = System.getProperty("user.dir");

                    // config.staticFiles.add(String.format("%s/build/resources/public",
                    // executionPath),
                    // Location.EXTERNAL);

                    config.staticFiles.add(cfg -> {
                        cfg.mimeTypes.add(ContentType.TEXT_CSS);
                        cfg.directory = String.format("%s/build/resources/public", executionPath);
                        cfg.location = Location.EXTERNAL;
                    });

                    // json mapper
                    Gson gson = new GsonBuilder().create();
                    JsonMapper gsonMapper = new JsonMapper() {
                        @Override
                        public String toJsonString(@NotNull Object obj, @NotNull Type type) {
                            return gson.toJson(obj, type);
                        }

                        @Override
                        public <T> T fromJsonString(@NotNull String json, @NotNull Type targetType) {
                            return gson.fromJson(json, targetType);
                        }
                    };
                    config.jsonMapper(gsonMapper);
                }));

        bind(ApplicationStarter.class).in(Scopes.SINGLETON);

    }
}