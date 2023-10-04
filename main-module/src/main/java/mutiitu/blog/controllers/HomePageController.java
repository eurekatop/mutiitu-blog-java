package mutiitu.blog.controllers;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import mutiitu.blog.components.home.HomePage;

@Controller
public class HomePageController extends JavalinController{
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private HomePage homePage;



    
    @Path(Value = "/homepage")
    @Method(Value = "GET" ) //TODO: refactor
    public HtmlResponse homePage () {

        homePage.init("Home Page");

        return new HtmlResponse(homePage);
    }


}
