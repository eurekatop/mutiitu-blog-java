package mutiitu.blog.controllers.admin;

import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;

import mutiitu.blog.components.admin.pages.AuthorFormPageComponent;
import mutiitu.blog.components.admin.pages.BlogFormPageComponent;
import mutiitu.blog.components.admin.pages.BlogListPageComponent;
import mutiitu.blog.components.admin.pages.ConfigPageComponent;
import mutiitu.blog.components.admin.pages.NewEntryPageComponent;
import mutiitu.blog.layouts.admin.AdminLayout;
import mutiitu.blog.layouts.admin.LoginLayout;
import mutiitu.blog.models.dto.BlogEntryInputDto;
import mutiitu.blog.models.dto.ResumeInputDto;
import mutiitu.blog.services.BlogEntryService;

import com.mutiitu.annotations.Transactional;
import com.mutiitu.dao.MigrateDatabase;
import com.mutiitu.domain.BlogEntryModel;
import com.mutiitu.domain.cms.CmsEntryModel;
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
    LoginLayout loginLayout;


    @Inject
    ConfigPageComponent configPageComponent;

    @Inject
    NewEntryPageComponent newEntryPageComponent;

    @Inject
    AuthorFormPageComponent authorFormPageComponent;

    @Inject
    BlogEntryService blogEntryService;    



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
    public HttpResponse page(String page) {
        switch (page) {
            case "login":
                 return loginLayout.render();

            case "config":
                 return new HtmlResponse(configPageComponent);
            case "new-entry":
                 return new HtmlResponse(newEntryPageComponent);
            case "author-form":
                 return new HtmlResponse(authorFormPageComponent);
            case "blog-form": {
                var id = ctx.queryParam("id");
                return blogUpsert(id);
            }
                 
            case "blog-list":
                 return blogList();
            default:
                break;
        }
        return new HtmlResponse("");
    }

    @Path(Value = "/admin/action")
    @Method(Value = "POST") // TODO: refactor
    public HttpResponse action(String page) throws Exception{
        switch (page) {
            case "login": {
                var email = ctx.formParam("email");
                var password = ctx.formParam("password");
                if ("admin@admin.local".equals(email) && "XWAJ3dFEwM3Nxtd".equals(password) ) {
                    ctx.header("HX-Redirect", "/admin");
                    var cookie1 = String.format("mmu_hash=%s; path=/; HttpOnly; Secure; SameSite=Lax",password);
                    var cookie2 = String.format("mmu_user=%s; path=/; HttpOnly; Secure; SameSite=Lax",email);
                    var cookie3 = String.format("mmu_rol=%s; path=/; HttpOnly; Secure; SameSite=Lax","admin");
                    ctx.header("Set-cookie", cookie1);
                    ctx.header("Set-cookie", cookie2);
                    ctx.header("Set-cookie", cookie3);
                    return new JsonResponse("go!");
                }

                ctx.status(401);
                return new StringResponse("na nai");
            }
            case "blog-delete": {
                var id = ctx.queryParam("id");
                return blogDelete(id);
            }
            default:
                break;
        }
        return new JsonResponse("");
    }



    @Transactional
    protected HtmlResponse blogUpsert(String id) {
        if ( id == null ) {
            return new HtmlResponse(new BlogFormPageComponent());
        }
        else {
            var _id = Integer.parseInt(id);
            var blog = blogEntryService.GetBydId(_id);

            Gson gson = new Gson();
            var blogDto = gson.fromJson(gson.toJson(blog), BlogEntryInputDto.class );

            return new HtmlResponse(new BlogFormPageComponent(blogDto));
        }
   }

    @Transactional
    protected JsonResponse blogDelete(String id) throws Exception{
        if ( id == null ) {
            throw new Exception("error");
        }
        else {
            var _id = Integer.parseInt(id);
            var blog = blogEntryService.GetBydId(_id);

            if (blog == null ) {
                throw new Exception("error1");
            }

            blogEntryService.DeleteBydId(_id);

            Gson gson = new Gson();
            var blogDto = gson.fromJson(gson.toJson(blog), BlogEntryInputDto.class );

            return new JsonResponse(blogDto);
        }
   }


    @Transactional
    protected HtmlResponse blogList() {
        var blogs = blogEntryService.GetBlogs(1000);

        Gson gson = new Gson();
        Type listType = new TypeToken<List<BlogEntryInputDto>>() {}.getType();
        List<BlogEntryInputDto>  model = gson.fromJson(gson.toJson(blogs), listType);

        
        return new HtmlResponse(new BlogListPageComponent(model));
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
