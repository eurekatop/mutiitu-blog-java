package mutiitu.blog.core;

import static com.google.inject.Guice.createInjector;

import mutiitu.blog.core.di.CoreModule;

public class Application {

    public static void main(String[] args) {
        createInjector(new CoreModule()).getInstance(ApplicationStarter.class).run(args);
    }
}