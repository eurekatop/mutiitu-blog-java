package mutiitu.framework.core.di;

import java.nio.file.Path;
import java.util.ArrayList;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import io.javalin.Javalin;
import mutiitu.blog.components.HeaderService;
import mutiitu.blog.components.HeaderServiceImpl;
import mutiitu.blog.routes.BlogRouter;
import mutiitu.framework.core.ApplicationStarter;
import mutiitu.framework.core.AutoShutdownPlugin;
import mutiitu.framework.core.Router;

public class CoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(HeaderService.class).to(HeaderServiceImpl.class);


        //binder().requireExplicitBindings();
        //install(new JavalinModule());
        //install(new MessageModule());

        bind(Javalin.class).toInstance(Javalin.create(
            config -> {
            var rootPath = Path.of(System.getProperty("user.dir")).getParent();
            var paths = new ArrayList<Path>();
            paths.add( Path.of(rootPath + "/main-module/src"));
            paths.add( Path.of(rootPath + "/persistence-module/src"));

            var autoShutdownPlugin = new AutoShutdownPlugin(paths);

            config.plugins.register(autoShutdownPlugin);                
            }
        ));

        bind(ApplicationStarter.class).in(Scopes.SINGLETON);

    }
}   