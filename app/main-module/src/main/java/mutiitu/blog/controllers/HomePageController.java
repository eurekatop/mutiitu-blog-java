package mutiitu.blog.controllers;

import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.mutiitu.annotations.Transactional;
import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;
import com.mutiitu.framework.core.http.responses.JsonResponse;


import mutiitu.blog.components._model.AuthorUIModel;
import mutiitu.blog.components._model.BlogPostUIModel;
import mutiitu.blog.components._model.CardUIModel;
import mutiitu.blog.components.blogpost.BlogPostUIComponent;
import mutiitu.blog.components.card.CardUIComponent;
import mutiitu.blog.components.editor.EditorUIComponent;
import mutiitu.blog.components.home.HomePage;
import mutiitu.blog.components.infinitescroll.InfiniteScrollUIComponent;
import mutiitu.blog.components.markdown.MarkdownUIComponent;
import mutiitu.blog.layouts.components.ComponentsLayout;
import mutiitu.blog.layouts.home.HomeLayout;
import mutiitu.blog.layouts.resume.ResumeLayout;
import mutiitu.blog.services.BlogEntryService;

@Controller
public class HomePageController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    // TODO: inject depenencies in function

    @Inject
    private HomePage homePage;
    @Inject
    private CardUIComponent cardUIComponent;

    @Inject
    private BlogEntryService blogEntryService;

    @Inject
    HomeLayout homeLayout;

    @Inject
    ResumeLayout resumeLayout;

    @Inject
    ComponentsLayout componentsLayout;


    @Path(Value = "/")
    @Method(Value = "GET")
    public HttpResponse Home() {

        try {
            return homeLayout.render();
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }

    @Path(Value = "/resume")
    @Method(Value = "GET")
    public HttpResponse Resume() {

        try {
            return resumeLayout.render();
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }


    @Path(Value = "/components")
    @Method(Value = "GET")
    public HttpResponse Components() {

        try {
            return componentsLayout.render();
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }


    @Transactional
    @Path(Value = "/homepage")
    @Method(Value = "GET")
    public HttpResponse homePage() {

        try {
            var blogIds = blogEntryService.GetAllBlogIds();
            var blogs = blogEntryService.GetBlogs(10);

            homePage.init("Home Page", blogIds, blogs);

            return new HtmlResponse(homePage);
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }

    @Path(Value = "/card")
    @Method(Value = "GET")
    public HttpResponse Card() {
        try {

            // TODO: change pebble template call constructor with some syntax
            var card = new CardUIModel();
            card.title = "com xerra";
            card.content = "com xerra";
            card.subtitle = "fdfds";

            cardUIComponent.init(card);

            return new HtmlResponse(cardUIComponent);
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }

    @Path(Value = "/blogpost")
    @Method(Value = "GET")
    public HttpResponse BlogPost() {
        try {

            // TODO: change pebble template call constructor with some syntax
            var author = new AuthorUIModel();
            author.name = "ds";
            author.surname = "fdds";

            var blog = new BlogPostUIModel();
            blog.title = "THFGDSGD ";
            blog.subtitle = "dff ds";
            blog.content = "Thfjds fdsf hds";

            var blogUiComponent = new BlogPostUIComponent();
            blogUiComponent.init(blog, author);

            return new HtmlResponse(blogUiComponent);
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }

    @Path(Value = "/infinitescroll")
    @Method(Value = "GET")
    public HttpResponse InfiniteScroll() {
        try {

            var infiniteScroll = new InfiniteScrollUIComponent();
            // infiniteScroll.init(blog, author);

            return new HtmlResponse(infiniteScroll);
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }

    @Path(Value = "/markdown")
    @Method(Value = "GET")
    public HttpResponse Markdown() {
        try {

            var uic = new MarkdownUIComponent();
            uic.init("dfsfdsjffsjfs fs");

            return new HtmlResponse(uic);
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }

    @Path(Value = "/editor")
    @Method(Value = "GET")
    public HttpResponse Editor() {
        try {

            var uic = new EditorUIComponent();
            uic.init("Hola melón");

            return new HtmlResponse(uic);
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }

}
