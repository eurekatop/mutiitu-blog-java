package mutiitu.blog.layouts.resume;

import com.google.inject.Inject;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import mutiitu.blog.components.card.CardUIComponent;
import mutiitu.blog.components.markdown.MarkdownUIComponent;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResumeLayout {

    @Inject
    CardUIComponent cardUIComponent;

    @Inject
    MarkdownUIComponent markdownUIComponent;

    @Inject
    MarkdownUIComponent markdownUIComponent2;


    public HttpResponse render() {
        try {

            markdownUIComponent.init(content);
            //markdownUIComponent2.init(content2);
            
            // "/mutiitu/blog/layouts/home/HomeLayout.html"
            // TODO: GC
            PebbleEngine engine = new PebbleEngine.Builder().build();
            PebbleTemplate compiledTemplate = engine.getTemplate("mutiitu/blog/layouts/resume/ResumeLayout.html");
            Map<String, Object> context = new HashMap<>();
            context.put("BASE_PATH", System.getenv().getOrDefault("BASE_PATH", "/"));

            var items = new ArrayList<String>();
            items.add("a ");
            items.add("b ");
            items.add("bfdjfdslfjldf  ");

            context.put("name", "Mitchell");

            context.put("items", items);
            context.put("card", cardUIComponent);
            context.put("markdown", markdownUIComponent);
            context.put("markdown2", markdownUIComponent2);
            context.put("document", content);

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

    public String content = """
        Proof of Concept (POC) 
        ======================""";

}
