package mutiitu.blog.core;

import com.google.inject.AbstractModule;

import mutiitu.blog.components.Header;
import mutiitu.blog.components.HeaderService;
import mutiitu.blog.components.HeaderServiceImpl;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(HeaderService.class).to(HeaderServiceImpl.class);

        //bind(Header.class).to(HeaderImpl.class).in(Scopes.SINGLETON);
        bind(Header.class).toInstance(new Header() {
            
        });
    }
}   