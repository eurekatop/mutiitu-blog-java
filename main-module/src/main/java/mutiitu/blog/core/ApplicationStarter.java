package mutiitu.blog.core;


import com.google.inject.Inject;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

public class ApplicationStarter {

    private final Javalin javalin;
    private final Router router;

    @Inject
    public ApplicationStarter(Javalin javalin, Router router) {
        this.javalin = javalin;
        this.router = router;
    }

    public void run(String... args) {
        
        JavalinThymeleaf.init();

        
        router.bind();
        javalin.start(8080);
    }
}