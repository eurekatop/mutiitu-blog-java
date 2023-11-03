package mutiitu.blog.controllers.rest;

import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import mutiitu.blog.components.BlogEntryComponent;
import mutiitu.blog.components.Header3;
import mutiitu.blog.models.dto.BlogEntryInputDto;
import mutiitu.blog.models.dto.CMSEntryInputDto;
import mutiitu.blog.services.BlogEntryService;
import com.mutiitu.annotations.Transactional;
import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.JsonResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse.HttpResponseType;
import com.mutiitu.framework.utils.FormDataParser;

@Controller
public class CMSEntryController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private final BlogEntryService blogEntryService;

    @Inject
    public CMSEntryController(BlogEntryService blogEntryService) {
        this.blogEntryService = blogEntryService;
    }

    @Transactional
    @Path(Value = "/cms-entry/post")
    @Method(Value = "POST")
    public HttpResponse post() throws Exception {

        try {
            // Gson gson = new GsonBuilder().create();
            // var data = gson.fromJson(ctx.body(), CMSEntryInputDto.class);

            var data = FormDataParser.parseFormAsClass(ctx, CMSEntryInputDto.class);
            return new StringResponse(data.toString());

        } catch (Exception ex) {
            logger.error(null, ex);
            throw new Exception("kkk");
        }

        // var result = blogEntryService.Create(data);
        //
        // return new JsonResponse(result);
        //
        // var data = gson.fromJson(ctx.body(), BlogEntryInputDto.class);
        //
        //
        // var contentType = ctx.queryParam("content-type");
        // if (contentType == null) {
        // contentType = "html";
        // }
        //
        // var result = blogEntryService.GetBydId(id);
        //
        // switch (contentType) {
        // case "json":
        // return new JsonResponse(result);
        // default:
        // return new HtmlResponse(blogEntryComponent.init(result));
        // }

    }

}
