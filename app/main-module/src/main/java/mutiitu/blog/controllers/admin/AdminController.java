package mutiitu.blog.controllers.admin;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import io.javalin.Javalin;
import mutiitu.blog.components.HeaderService;

import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;

@Controller
public class AdminController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private Javalin javalin;

    @Inject
    private HeaderService headerService;


    @Path(Value = "/admin/home")
    public void aa () {
        logger.info("me estan llamando desde core");
        logger.info(javalin.toString());
    }
}
    
