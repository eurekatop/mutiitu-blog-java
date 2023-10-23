package mutiitu.blog.controllers.admin;

import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
//import com.google.inject.servlet.SessionScoped;

import io.javalin.Javalin;
import mutiitu.blog.components.HeaderService;
import mutiitu.blog.models.dto.BlogEntryInputDto;
import mutiitu.blog.models.dto.ResumeInputDto;
import mutiitu.blog.services.BlogEntryService;
import mutiitu.blog.services.TestService;

import com.mutiitu.annotations.Transactional;
import com.mutiitu.dao.BlogEntryDao;
import com.mutiitu.domain.BlogEntryModel;
import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.JsonResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;

@Controller
public class AdminController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private Javalin javalin;

    @Inject
    private BlogEntryService blogEntryService;

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
        return new JsonResponse(testService.TestA());
    }

    @Transactional
    @Path(Value = "/admin/add")
    @Method(Value = "POST") // TODO: refactor
    public HttpResponse test(String asset) {

        Gson gson = new GsonBuilder()
                .create();

        var json = """
                {
                    "id":"1",
                    "title":"pedro",
                    "subtitle":"pedro",
                    "authoriId":"1",
                    "content":"kk"
                }
                """;
        try {
            var data = gson.fromJson(ctx.body(), BlogEntryInputDto.class);

            // @tech: Mapping
            var blogEntryModel = new BlogEntryModel();
            blogEntryModel.setAuthorId(data.authorId);
            blogEntryModel.setTitle(data.title);
            blogEntryModel.setSubtitle(data.subtitle);
            blogEntryModel.setContent(data.content);
            // tech:@

            blogEntryService.AddBlog(blogEntryModel);

            return new JsonResponse(blogEntryModel);

        } catch (Exception ex) {
            // tech: errors
            return new StringResponse(ex.getMessage());

        }
    }

    @Transactional
    @Path(Value = "/admin/resume/add")
    @Method(Value = "POST") // TODO: refactor
    public HttpResponse ResumeAdd(String id) throws Exception {
        Gson gson = new GsonBuilder()
                .create();

        System.out.println(ctx.body());        
        ResumeInputDto data = null;
        try {
            data = gson.fromJson(ctx.body(), ResumeInputDto.class);
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }

        
        return new JsonResponse(data);
    }

    @Transactional
    @Path(Value = "/admin/resume/get")
    @Method(Value = "GET") // TODO: refactor
    public HttpResponse ResumeGet(int id) throws Exception {
        
        throw new Exception();

    }


}
