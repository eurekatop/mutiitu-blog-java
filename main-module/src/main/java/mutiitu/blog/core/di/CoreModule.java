package mutiitu.blog.core.di;

import java.nio.file.Path;
import java.util.ArrayList;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import io.javalin.Javalin;
import mutiitu.blog.components.HeaderService;
import mutiitu.blog.components.HeaderServiceImpl;
import mutiitu.blog.core.ApplicationStarter;
import mutiitu.blog.core.AutoShutdownPlugin;
import mutiitu.blog.core.Router;
import mutiitu.blog.routes.BlogRouter;

public class CoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(HeaderService.class).to(HeaderServiceImpl.class);


        binder().requireExplicitBindings();
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
        
        bind(Router.class).to(BlogRouter.class);



//        bind(Router.class).toInstance(Javalin.create());


        bind(ApplicationStarter.class).in(Scopes.SINGLETON);

        //bind(ModelCrud.class).to(ModelCrudImpl.class).


        //bind(Header.class).to(HeaderImpl.class).in(Scopes.SINGLETON);
    }
}   