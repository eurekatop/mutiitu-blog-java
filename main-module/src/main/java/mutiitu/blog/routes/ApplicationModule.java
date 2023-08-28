package mutiitu.blog.routes;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import io.javalin.Javalin;
import mutiitu.blog.components.HeaderService;
import mutiitu.blog.components.HeaderServiceImpl;
import mutiitu.blog.core.Router;
import mutiitu.blog.core.di.CoreModule;

public class ApplicationModule  extends CoreModule {
    @Override
    protected void configure() {
        super.configure();

        binder().requireExplicitBindings();
        //install(new JavalinModule());
        //install(new MessageModule());

        bind(HeaderService.class).to(HeaderServiceImpl.class);
        bind(Router.class).to(BlogRouter.class);
    }
}   
