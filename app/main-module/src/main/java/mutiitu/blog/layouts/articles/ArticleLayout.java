package mutiitu.blog.layouts.articles;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import com.mutiitu.annotations.Transactional;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import mutiitu.blog.components.BlogEntryComponent;
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

public class ArticleLayout {

    public BlogEntryInputDto blogEntryInputDto;


    public HttpResponse render() {
        try {
            var blogEntryComponent = new BlogEntryComponent();

            var markdown = new MarkdownUIComponent();

            // TODO: GC
            PebbleEngine engine = new PebbleEngine.Builder().build();
            PebbleTemplate compiledTemplate = engine.getTemplate("mutiitu/blog/layouts/articles/index.html");
            Map<String, Object> context = new HashMap<>();


            System.out.println(blogEntryInputDto.content);

            blogEntryComponent.init(blogEntryInputDto);
            
            markdown.init(blogEntryInputDto.content);

            context.put("blog", blogEntryInputDto);
            context.put("blogEntryComponent", markdown);




            var items = new ArrayList<String>();
            context.put("items", items);

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

}
