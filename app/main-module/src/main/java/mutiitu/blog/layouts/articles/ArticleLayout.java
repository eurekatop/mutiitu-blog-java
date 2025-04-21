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
import mutiitu.blog.components.markdown.MarkdownUIComponent;
import mutiitu.blog.models.dto.BlogEntryInputDto;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArticleLayout {

    public BlogEntryInputDto blogEntryInputDto;


    public HttpResponse render() {
        try {
            var blogEntryComponent = new BlogEntryComponent();

            var markdown = new MarkdownUIComponent();

            // TODO: GC
            // TODO: title in CMS
            PebbleEngine engine = new PebbleEngine.Builder().build();
            PebbleTemplate compiledTemplate = engine.getTemplate("mutiitu/blog/layouts/articles/article.html");
            Map<String, Object> context = new HashMap<>();


            System.out.println(blogEntryInputDto.content);

            blogEntryComponent.init(blogEntryInputDto);
            
            markdown.init(blogEntryInputDto.content);

            String jsonLd = generateJsonLd(blogEntryInputDto);

            context.put("jsonLd", jsonLd);
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

    private String generateJsonLd(BlogEntryInputDto blogEntryInputDto) {

        Gson gson = new Gson();
        String escapedContent = gson.toJson(blogEntryInputDto.content);

        // Create the JSON-LD structured data dynamically based on the blog entry
        return String.format(
            "{\n" +
            "  \"@context\": \"https://schema.org\",\n" +
            "  \"@type\": \"Article\",\n" +
            "  \"headline\": \"%s\",\n" +
            "  \"alternativeHeadline\": \"%s\",\n" +
            "  \"articleBody\": %s,\n" +
            "  \"description\": \"%s\",\n" +
            "  \"author\": {\n" +
            "    \"@type\": \"Person\",\n" +
            "    \"name\": \"%s\"\n" +
            "  },\n" +
            "  \"publisher\": {\n" +
            "    \"@type\": \"Organization\",\n" +
            "    \"name\": \"Mutiitu\",\n" +
            "    \"logo\": {\n" +
            "      \"@type\": \"ImageObject\",\n" +
            "      \"url\": \"%s\"\n" +
            "    }\n" +
            "  }\n" +
            "}", 
            blogEntryInputDto.title, 
            blogEntryInputDto.subtitle,
            escapedContent,
            blogEntryInputDto.resume,
            blogEntryInputDto.authorId,
            "https://mutiitu.com/logo.png" // Replace with your logo URL
        );
    }


}
