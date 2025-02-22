package mutiitu.blog.controllers.home2;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;


import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.JsonResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;

import mutiitu.blog.layouts.home2.Home2Layout;
import mutiitu.blog.services.BlogEntryService;

public class Home2Controller extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    Home2Layout home2Layout;

    @Inject
    BlogEntryService blogEntryService;



    @Path(Value = "/home2/:param1/:param2")
    @Method(Value = "GET") // TODO: refactor
    public StringResponse index(String param1, String param2) {
        logger.info("Que me dices? tu" + param1 + " " + param2);

        // get blogs from service
        //var blogs = blogEntryService.GetAllBlogPartial();

        // get blogs from service
        //var blogs1 = blogEntryService.GetAllBlogJooq();
        //for (var blog1 : blogs1) {
        //    logger.info("Blog: " + blog1.getTitle());
        //}
//
        //var blogs2 = blogEntryService.GetAllBlogPartialJooq();
        //for (var blog1 : blogs2) {
        //    logger.info("Blog partial: " + blog1.title);
        //}

        var blogs3 = blogEntryService.GetAllBlogPartialByDto();
        for (var blog1 : blogs3) {
            logger.info("Blog partial 2: " + blog1.title);
        }
        var r = "";

        for (var blog : blogs3) {
            r += blog.id+ " ";
            r += blog.resume+ " ";
            r += blog.subtitle+ " ";
            r += blog.title+ " ";
        }

        return new StringResponse(r + " " + param1 + " " + param2);
    }  

    @Path(Value = "/home2/test")
    @Method(Value = "GET")
    public HttpResponse Home() {

        try {
            return home2Layout.render();
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }  


    
}
