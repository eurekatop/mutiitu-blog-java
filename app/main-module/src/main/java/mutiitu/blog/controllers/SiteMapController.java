package mutiitu.blog.controllers;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import io.javalin.Javalin;
import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import mutiitu.blog.components.Header2;
import mutiitu.blog.components.HeaderService;
import mutiitu.blog.services.BlogEntryService;
import mutiitu.blog.services.CMSEntryService;

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
public class SiteMapController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private Javalin javalin;
    @Inject
    private Header2 header2;

    @Inject
    private HeaderService headerService;

    @Inject
    private BlogEntryService blogEntryService;

    @Inject
    private CMSEntryService cmsEntryService;



    public String generateSitemap() {
        List<Integer> blogsIds = blogEntryService.SelectAllIds();
        List<String> slugs = cmsEntryService.SelectAllSlugs();

        String baseUrl = "https://blog.mutiitu.com";

        StringBuilder sitemap = new StringBuilder();
        sitemap.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sitemap.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");

        // Afegim les rutes estàtiques
        sitemap.append("  <url><loc>").append(baseUrl).append("/</loc></url>\n");
        sitemap.append("  <url><loc>").append(baseUrl).append("/contact/me</loc></url>\n");
        sitemap.append("  <url><loc>").append(baseUrl).append("/posts/list</loc></url>\n");
        sitemap.append("  <url><loc>").append(baseUrl).append("/entries/list</loc></url>\n");
        

        
        // ... afegeix les que vulguis

        // Afegim les dinàmiques
        for (Integer id : blogsIds) {
            sitemap.append("  <url><loc>").append(baseUrl).append("/articles/").append(id.toString()).append("</loc></url>\n");
        }

        // Afegim les dinàmiques
        for (String slug : slugs) {
            sitemap.append("  <url><loc>").append(baseUrl).append("/entry/").append(slug.toString()).append("</loc></url>\n");
        }



        sitemap.append("</urlset>");

        return sitemap.toString();
    }


    @Path(Value = "/sitemap.xml")
    @Method(Value = "GET") 
    public StringResponse add() {
        return new StringResponse(generateSitemap());
    }

    @Path(Value = "/f3515de6c3394f55931e548afd3b42ba.txt")
    @Method(Value = "GET") 
    public StringResponse bingIndexNow() {
        return new StringResponse("f3515de6c3394f55931e548afd3b42ba.txt");
    }


}
