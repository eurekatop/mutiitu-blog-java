package mutiitu.blog.controllers;

import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.mutiitu.annotations.Transactional;
import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.JsonResponse;
import mutiitu.blog.layouts.articles.ArticleLayout;
import mutiitu.blog.models.dto.BlogEntryInputDto;
import mutiitu.blog.services.BlogEntryService;

@Controller
public class ArticlesPageController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    // TODO: inject depenencies in function
    @Inject
    private BlogEntryService blogEntryService;

    @Transactional
    @Path(Value = "/articles")
    @Method(Value = "GET")
    public HttpResponse Home(String id) {

        try {
            var _id = Integer.parseInt(id);
            var blog = blogEntryService.GetBydId(_id);

            //TODO: automapper
            Gson gson = new Gson();
            BlogEntryInputDto  model = gson.fromJson(gson.toJson(blog), BlogEntryInputDto.class);

        
            ArticleLayout articlesLayout = new ArticleLayout();
            articlesLayout.blogEntryInputDto = model;



            return articlesLayout.render();
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }

}
