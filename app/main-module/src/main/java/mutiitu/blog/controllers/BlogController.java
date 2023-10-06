package mutiitu.blog.controllers;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import io.javalin.Javalin;
import com.mutiitu.framework.core.Router;
import com.mutiitu.framework.core.annotations.Controller;

@Controller
public class BlogController implements Router{
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private final Javalin javalin;
    //private final MessageService service;

    @Inject
    public BlogController(Javalin javalin) {
        this.javalin = javalin;
    }

    @Override
    public void bind() {
        logger.debug("binder");
        javalin.get("/blog", ctx -> ctx.result("Hello Woddd") );
    }
    
}
