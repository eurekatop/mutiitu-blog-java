package mutiitu.blog.routes;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import io.javalin.Javalin;
import mutiitu.framework.core.Router;
import mutiitu.framework.core.annotations.Controller;

@Controller
public class BlogRouter implements Router{
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private final Javalin javalin;
    //private final MessageService service;

    @Inject
    public BlogRouter(Javalin javalin) {
        this.javalin = javalin;
    }

    @Override
    public void bind() {
        logger.debug("binder");
        javalin.get("/blog", ctx -> ctx.result("Hello Woddd") );
    }
    
}
