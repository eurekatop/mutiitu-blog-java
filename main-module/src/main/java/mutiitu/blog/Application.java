package mutiitu.blog;

import static com.google.inject.Guice.createInjector;

import mutiitu.framework.core.ApplicationStarter;

// thx: https://github.com/konstantins-buts/Javalin-Guice-Demo
public class Application {

    public static void main(String[] args) {
        createInjector(new ApplicationModule()).getInstance(ApplicationStarter.class).run(args);
    }
}