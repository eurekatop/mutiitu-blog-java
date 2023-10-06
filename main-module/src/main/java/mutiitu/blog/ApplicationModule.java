package mutiitu.blog;

import com.google.inject.AbstractModule;

import mutiitu.blog.components.Header;
import mutiitu.blog.components.Header2;
import mutiitu.blog.components.Header3;
import mutiitu.blog.components.HeaderService;
import mutiitu.blog.components.HeaderServiceImpl;
import mutiitu.blog.components.home.HomePage;
import mutiitu.blog.controllers.BlogController;
import com.mutiitu.framework.core.Router;
import com.mutiitu.framework.core.di.CoreModule;
import com.mutiitu.framework.core.ui.UIComponentFactory;

public class ApplicationModule  extends AbstractModule {
    @Override
    protected void configure() {
        super.configure();

        //binder().requireExplicitBindings();
        install(new CoreModule());
        //install(new MessageModule());

        bind(Header.class).asEagerSingleton();
        bind(Header2.class).asEagerSingleton();
        bind(Header3.class).asEagerSingleton();

        bind(HeaderService.class).to(HeaderServiceImpl.class);


        // TODO: discover with anotation
        bind(Router.class).to(BlogController.class);
        //bind(HelloWorldRouter.class).toInstance(new HelloWorldRouter(null));

        // add template components...... maybe discover with annotations.....
        //UIComponentFactory.AddComponent("Header3", new Header3() );
        //UIComponentFactory.AddComponent("HomePage", new HomePage() );
    }
}   
