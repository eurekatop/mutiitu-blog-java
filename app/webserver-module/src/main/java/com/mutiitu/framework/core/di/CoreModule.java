package com.mutiitu.framework.core.di;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.sql.DataSource;

import org.eclipse.jetty.server.session.DatabaseAdaptor;
import org.eclipse.jetty.server.session.DefaultSessionCache;
import org.eclipse.jetty.server.session.JDBCSessionDataStoreFactory;
import org.eclipse.jetty.server.session.NullSessionCache;
import org.eclipse.jetty.server.session.SessionCache;
import org.eclipse.jetty.server.session.SessionHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

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
import io.javalin.validation.ValidationException;
import jakarta.transaction.Transactional;

import com.mutiitu.framework.core.ApplicationStarter;
import com.mutiitu.framework.core.AutoShutdownPlugin;
import com.mutiitu.framework.core.Router;
import com.mutiitu.framework.core.adapters.ValidationExceptionAdapter;
import com.mutiitu.framework.core.ui.RouterImpl;

public class CoreModule extends AbstractModule {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());


    //TODO: refactor, for setting store sessions
    private final DataSource dataSource;

    public CoreModule(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    private static JDBCSessionDataStoreFactory jdbcDataStoreFactory(DataSource dataSource) {
        DatabaseAdaptor databaseAdaptor = new DatabaseAdaptor();
        // databaseAdaptor.setDriverInfo(driver, url);
        databaseAdaptor.setDatasource(dataSource); // you can set data sourcehere
        // (for connection pooling, etc)
        JDBCSessionDataStoreFactory jdbcSessionDataStoreFactory = new JDBCSessionDataStoreFactory();
        jdbcSessionDataStoreFactory.setDatabaseAdaptor(databaseAdaptor);

        return jdbcSessionDataStoreFactory;
    }

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
                    // ----------------------------------------------------------------------------------
                    GsonBuilder gsonBuilder = new GsonBuilder();

                    gsonBuilder.registerTypeAdapter(ValidationException.class, new ValidationExceptionAdapter() );

                    gsonBuilder.excludeFieldsWithoutExposeAnnotation();

                    Gson gson = gsonBuilder.create();
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
                    // ----------------------------------------------------------------------------------

                    // session handler
                    // ----------------------------------------------------------------------------------
                    Supplier<SessionHandler> sessionHandler = new Supplier<SessionHandler>() {
                        @Override
                        public SessionHandler get() {
                            System.out.println("-------------------------------------------------------");
                            System.out.println("-------------------------------------------------------");
                            System.out.println("-------------------------------------------------------");
                            System.out.println("-------------------------------------------------------");
                            System.out.println("-------------------------------------------------------");
                            System.out.println("-------------------------------------------------------");
                            System.out.println("-------------------------------------------------------");
                            System.out.println("-------------------------------------------------------");
                            System.out.println("-------------------------------------------------------");
                            System.out.println("-------------------------------------------------------");
                            System.out.println("-------------------------------------------------------");
                            System.out.println("-------------------------------------------------------");
                            System.out.println("-------------------------------------------------------");
                            System.out.println("-------------------------------------------------------");
                            System.out.println("-------------------------------------------------------");

                            SessionHandler sessionHandler = new SessionHandler();
                            SessionCache sessionCache = new DefaultSessionCache(sessionHandler); // new
                                                                                                 // DefaultSessionCache(sessionHandler);
                            sessionCache.setSessionDataStore(
                                    CoreModule.jdbcDataStoreFactory(dataSource)
                                            .getSessionDataStore(sessionHandler));
                            sessionHandler.setSessionCache(sessionCache);
                            sessionHandler.setHttpOnly(true);
                            // make additional changes to your SessionHandler here

                            // try {
                            //
                            // var c = dataSource.getConnection();
                            // c.setAutoCommit(false);
                            // } catch (SQLException e) {
                            // // TODO Auto-generated catch block
                            // e.printStackTrace();
                            // }

                            return sessionHandler;

                            // TODO Auto-generated method stub
                            // throw new UnsupportedOperationException("Unimplemented method 'get'");
                        }
                    };
                    config.jetty.sessionHandler(sessionHandler);
                    // ----------------------------------------------------------------------------------

                }));

        bind(ApplicationStarter.class).in(Scopes.SINGLETON);

    }
}