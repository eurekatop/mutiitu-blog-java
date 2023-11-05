package mutiitu.blog.controllers.rest;

import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.google.inject.Inject;

import mutiitu.blog.models.dto.AuthorInputDto;
import mutiitu.blog.models.dto.BlogEntryInputDto;
import mutiitu.blog.models.dto.CMSEntryInputDto;
import mutiitu.blog.services.AuthorService;
import mutiitu.blog.services.BlogEntryService;
import mutiitu.blog.services.CMSEntryService;
import com.mutiitu.annotations.Transactional;
import com.mutiitu.domain.AuthorModel;
import com.mutiitu.domain.BlogEntryModel;
import com.mutiitu.domain.cms.CmsEntryModel;
import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.JsonResponse;
import com.mutiitu.framework.utils.FormDataParser;

@Controller
public class CMSEntryController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private final CMSEntryService cmsEntryService;
    private final AuthorService authorService;
    private final BlogEntryService blogEntryService;

    @Inject
    public CMSEntryController(CMSEntryService cmsEntryService, AuthorService authorService, BlogEntryService blogEntryService) { // TODO: inject in functions
        this.cmsEntryService = cmsEntryService;
        this.authorService = authorService;
        this.blogEntryService = blogEntryService;
    }

    @Path(Value = "/cms-entry/post")
    @Method(Value = "POST")
    @Transactional
    public HttpResponse post() throws Exception {

        // validate input
        ctx.formParamAsClass("title", String.class)
                .check(it -> !(it.isBlank() || it.isEmpty()), "Is empty!")
                .get();
        ctx.formParamAsClass("authorId", Integer.class)
                .check(it -> it > 0, "author id must be greater than 0!")
                .get();
        ctx.formParamAsClass("date", String.class)
                .check(it -> !(it.isBlank() || it.isEmpty()), "Is empty!")
                .get();

        // convert form input to inputdto
        var data = FormDataParser.parseFormAsClass(ctx, CMSEntryInputDto.class);

        // note: doma need 0 or negative to populate generate id key
        data.setId(-1);

        // convert input dto to domain model
        Gson gson = new Gson();
        CmsEntryModel model = gson.fromJson(gson.toJson(data), CmsEntryModel.class);

        // add to domain
        cmsEntryService.Add(model);

        // convert domain model to transfer object inputDto
        var resultDto = gson.fromJson(gson.toJson(model), CMSEntryInputDto.class);

        return new JsonResponse(resultDto);
    }


    @Path(Value = "/author-entry/post")
    @Method(Value = "POST")
    @Transactional
    public HttpResponse authorPost() throws Exception {

        // validate input
        ctx.formParamAsClass("name", String.class)
                .check(it -> !(it.isBlank() || it.isEmpty()), "Is empty!")
                .get();
        ctx.formParamAsClass("surname", String.class)
                .check(it -> !(it.isBlank() || it.isEmpty()), "Is empty!")
                .get();

        // convert form input to inputdto
        var data = FormDataParser.parseFormAsClass(ctx, AuthorInputDto.class);

        // note: doma need 0 or negative to populate generate id key
        data.id = -1;

        // convert input dto to domain model
        Gson gson = new Gson();
        AuthorModel model = gson.fromJson(gson.toJson(data), AuthorModel.class);

        // add to domain
        authorService.Add(model);

        // convert domain model to transfer object inputDto
        var resultDto = gson.fromJson(gson.toJson(model), AuthorInputDto.class);

        return new JsonResponse(resultDto);
    }


    @Path(Value = "/blog-post-entry/post")
    @Method(Value = "POST")
    @Transactional
    public HttpResponse blogPostPost() throws Exception {

        // validate input
        ctx.formParamAsClass("title", String.class)
                .check(it -> !(it.isBlank() || it.isEmpty()), "Is empty!")
                .get();
        ctx.formParamAsClass("subtitle", String.class)
                .check(it -> !(it.isBlank() || it.isEmpty()), "Is empty!")
                .get();
        ctx.formParamAsClass("content", String.class)
                .check(it -> !(it.isBlank() || it.isEmpty()), "Is empty!")
                .get();
        ctx.formParamAsClass("authorId", Integer.class)
                .check(it -> it > 0, "author id must be greater than 0!")
                .get();


        // convert form input to inputdto
        var data = FormDataParser.parseFormAsClass(ctx, BlogEntryInputDto.class);

        // convert input dto to domain model
        Gson gson = new Gson();
        BlogEntryModel model = gson.fromJson(gson.toJson(data), BlogEntryModel.class);

        logger.info("ADD BLOG DTO {}", model);

        // add to domain
        blogEntryService.AddBlog(model);

        // convert domain model to transfer object inputDto
        var resultDto = gson.fromJson(gson.toJson(model), BlogEntryInputDto.class);

        return new JsonResponse(resultDto);
    }

}
