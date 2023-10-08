package mutiitu.blog.controllers;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;

import mutiitu.blog.components.home.HomePage;

@Controller
public class HeaderController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private HomePage homePage;

    @Path(Value = "/header/get")
    @Method(Value = "GET") // TODO: refactor
    public HtmlResponse add(String name) {
        logger.info("Que me dices? " + name);

        homePage.init("Home Page");

        // return new StringResponse(homePage);
        return new HtmlResponse(homePage);
    }

}
