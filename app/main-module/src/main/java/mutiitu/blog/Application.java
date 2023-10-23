package mutiitu.blog;

import static com.google.inject.Guice.createInjector;

import com.mutiitu.framework.core.ApplicationStarter;
import com.mutiitu.framework.core.ui.UIComponentFactory;

// thx: https://github.com/konstantins-buts/Javalin-Guice-Demo
public class Application {

    public static void main(String[] args) {
        // createInjector(new ApplicationModule()).getInstance(ApplicationStarter.class).run(args);
        var injector = createInjector(new ApplicationModule());
        injector.getInstance(ApplicationStarter.class).run(args);

        // TODO: code smells
        // bootstrap core ??
        UIComponentFactory.configure ( injector );
    }
}