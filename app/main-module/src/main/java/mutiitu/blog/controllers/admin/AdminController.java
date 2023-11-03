package mutiitu.blog.controllers.admin;

import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;

import mutiitu.blog.components.admin.pages.ConfigPageComponent;
import mutiitu.blog.components.admin.pages.NewEntryPageComponent;
import mutiitu.blog.layouts.admin.AdminLayout;
import mutiitu.blog.models.dto.BlogEntryInputDto;
import mutiitu.blog.models.dto.ResumeInputDto;
import com.mutiitu.annotations.Transactional;
import com.mutiitu.dao.MigrateDatabase;
import com.mutiitu.domain.BlogEntryModel;
import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.JsonResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;

@Controller
public class AdminController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    MigrateDatabase migrateDatabase;

    @Inject
    AdminLayout adminLayout;

    @Inject
    ConfigPageComponent configPageComponent;

    @Inject
    NewEntryPageComponent newEntryPageComponent;

    

    @Path(Value = "/admin")
    public HttpResponse index() {
        return adminLayout.render();
    }


    @Transactional
    @Path(Value = "/admin/database/migrate")
    @Method(Value = "POST") // TODO: refactor
    public JsonResponse config() {
        // migrate BD
        var result = "";
        try {
            migrateDatabase.create();
            result = "OK";
        }
        catch ( Exception ex ){
            logger.error("Error en la migracion", ex);
            result="KO";
        }

        return new JsonResponse(result);
    }


    @Path(Value = "/admin/test")
    @Method(Value = "POST") // TODO: refactor
    public JsonResponse test() {
        return new JsonResponse("hello");
    }

    @Path(Value = "/admin/page")
    @Method(Value = "GET") // TODO: refactor
    public HtmlResponse page(String page) {
        switch (page) {
            case "config":
                 return new HtmlResponse(configPageComponent);
            case "new-entry":
                 return new HtmlResponse(newEntryPageComponent);
            default:
                break;
        }
        return new HtmlResponse("");
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

            //blogEntryService.AddBlog(blogEntryModel);

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
