package mutiitu.blog.controllers.admin;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
//import com.google.inject.servlet.SessionScoped;

import io.javalin.Javalin;
import mutiitu.blog.components.HeaderService;
import mutiitu.blog.services.TestService;

import com.mutiitu.annotations.Transactional;
import com.mutiitu.dao.BlogEntryDao;
import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.JsonResponse;

@Controller
public class AdminController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private Javalin javalin;

    @Inject
    private HeaderService headerService;

    @Inject
    private TestService testService;

    @Inject
    com.mutiitu.persistence.SQLiteDB SQLiteDB;

    @Path(Value = "/admin/home")
    public void aa() {
        logger.info("me estan llamando desde core");
        logger.info(javalin.toString());
        logger.info(testService.toString());
    }

    @Transactional
    @Path(Value = "/admin/test")
    public JsonResponse test() {

        // var tx = SQLiteDB.getTransactionManager();

        // with transactionManager ( remove @Transactional )
        // var result = tx.required(
        // () -> {
        // return testService.TestA();
        // });
        System.out.println("Admin controler " + Thread.currentThread().toString());

        return new JsonResponse(testService.TestA());
    }

}
