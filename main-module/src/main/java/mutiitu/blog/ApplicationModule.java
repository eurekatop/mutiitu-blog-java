package mutiitu.blog;

import com.google.inject.AbstractModule;

import mutiitu.blog.components.HeaderService;
import mutiitu.blog.components.HeaderServiceImpl;
import mutiitu.blog.routes.BlogRouter;
import mutiitu.blog.routes.HelloWorldRouter;
import mutiitu.framework.core.Router;
import mutiitu.framework.core.di.CoreModule;

public class ApplicationModule  extends AbstractModule {
    @Override
    protected void configure() {
        super.configure();

        //binder().requireExplicitBindings();
        install(new CoreModule());
        //install(new MessageModule());

        bind(HeaderService.class).to(HeaderServiceImpl.class);

        // TODO: discover with anotation
        bind(Router.class).to(BlogRouter.class);
        //bind(HelloWorldRouter.class).toInstance(new HelloWorldRouter(null));
    }
}   
