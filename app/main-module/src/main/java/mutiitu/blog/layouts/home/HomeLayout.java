package mutiitu.blog.layouts.home;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import com.mutiitu.annotations.Transactional;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import mutiitu.blog.components.card.CardUIComponent;
import mutiitu.blog.components.markdown.MarkdownUIComponent;
import mutiitu.blog.models.dto.BlogEntryInputDto;
import mutiitu.blog.services.BlogEntryService;

import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeLayout {

    //@Inject
    //CardUIComponent cardUIComponent;
    //@Inject
    //MarkdownUIComponent markdownUIComponent;
    //@Inject
    //MarkdownUIComponent markdownUIComponent2;

    @Inject
    BlogEntryService blogEntryService;


    public HttpResponse render() {
        try {

            // "/mutiitu/blog/layouts/home/HomeLayout.html"
            // TODO: GC
            PebbleEngine engine = new PebbleEngine.Builder().build();
            PebbleTemplate compiledTemplate = engine.getTemplate("mutiitu/blog/layouts/home/index.html");
            Map<String, Object> context = new HashMap<>();
            context.put("BASE_PATH", System.getenv().getOrDefault("BASE_PATH", "/"));


            List<BlogEntryInputDto> blogs = GetBlogs();
            context.put("blogs", blogs);

            var items = new ArrayList<String>();
            context.put("items", items);


            context.put("BASE_PATH", System.getenv().getOrDefault("BASE_PATH", "/"));

            Writer writer = new StringWriter();
            compiledTemplate.evaluate(writer, context);
            String output = writer.toString();
            return new HtmlResponse(output);

        } catch (Exception ex) {
            // TODO: errors
            ex.printStackTrace();
            return new StringResponse("output");
        }
    }

    @Transactional
    protected List<BlogEntryInputDto> GetBlogs(){
        var blogs = blogEntryService.GetBlogs(1000);
        //TODO: automapper
        Gson gson = new Gson();
        Type listType = new TypeToken<List<BlogEntryInputDto>>() {}.getType();
        List<BlogEntryInputDto>  model = gson.fromJson(gson.toJson(blogs), listType);

        return model;
    }
}
