package mutiitu.blog.layouts.cmsEntries;

import com.google.gson.Gson;
import com.mutiitu.domain.CmsEntryModel;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import mutiitu.blog.models.dto.BlogEntryInputDto;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class CmsEntriesLayoutDetail {

    public HttpResponse render(CmsEntryModel data) {
        try {
            PebbleEngine engine = new PebbleEngine.Builder().build();
            PebbleTemplate compiledTemplate = engine.getTemplate("mutiitu/blog/layouts/cmsEntries/detail.html");
            Map<String, Object> context = new HashMap<>();
            context.put("BASE_PATH", System.getenv().getOrDefault("BASE_PATH", "/"));

            String jsonLd = generateJsonLd(data);


            context.put("data", data);
            context.put("jsonLd", jsonLd);
            

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


        private String generateJsonLd(CmsEntryModel cmsEntryModel) {

        Gson gson = new Gson();
        String escapedContent = gson.toJson(cmsEntryModel.getContent());

        // Create the JSON-LD structured data dynamically based on the blog entry
        return String.format(
            "{\n" +
            "  \"@context\": \"https://schema.org\",\n" +
            "  \"@type\": \"Article\",\n" +
            "  \"headline\": \"%s\",\n" +
            "  \"articleBody\": %s,\n" +
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
            cmsEntryModel.getTitle(), 
            escapedContent,
            cmsEntryModel.getAuthorId(),
            "https://mutiitu.com/logo.png" // Replace with your logo URL
        );
    }



}
