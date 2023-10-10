package mutiitu.blog.controllers;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import mutiitu.blog.components.BlogEntryComponent;
import mutiitu.blog.components.Header3;
import mutiitu.blog.services.BlogEntryService;
import com.mutiitu.annotations.Transactional;
import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.JsonResponse;

@Controller
public class BlogController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private final BlogEntryService blogEntryService;

    @Inject
    public BlogController(BlogEntryService blogEntryService) {
        this.blogEntryService = blogEntryService;
    }

    @Inject
    BlogEntryComponent blogEntryComponent;

    @Transactional
    @Path(Value = "/blog/get")
    public HttpResponse GetById(int id) {

        var contentType = ctx.queryParam("content-type");
        if (contentType == null) {
            contentType = "html";
        }

        var result = blogEntryService.GetBydId(id);

        switch (contentType) {
            case "json":
                return new JsonResponse(result);
            default:
                return new HtmlResponse(blogEntryComponent.init(result));
        }

    }

}
