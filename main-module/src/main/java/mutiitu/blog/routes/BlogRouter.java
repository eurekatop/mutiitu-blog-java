package mutiitu.blog.routes;

import com.google.inject.Inject;

import io.javalin.Javalin;
import mutiitu.blog.core.Router;

public class BlogRouter implements Router{

    private final Javalin javalin;
    //private final MessageService service;

    @Inject
    public BlogRouter(Javalin javalin) {
        this.javalin = javalin;
    }

    @Override
    public void bind() {
        javalin.get("/blog", ctx -> ctx.result("Hello Woddd") );
    }
    
}
