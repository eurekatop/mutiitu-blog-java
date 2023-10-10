package mutiitu.blog.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;

import mutiitu.blog.components.home.HomePage;

@Controller
public class HeaderController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    // @Inject
    // private HomePage homePage;

    @Path(Value = "/header/get")
    @Method(Value = "GET") // TODO: refactor
    public HttpResponse add(String name) {
        logger.info("Que me dices? " + name);

        var p = new ArrayList<Integer>(List.of(1, 2, 3, 4, 5, 6, 7));

        // homePage.init("Home Page", p);

        // return new StringResponse(homePage);
        return new StringResponse("homePage");
    }

}
