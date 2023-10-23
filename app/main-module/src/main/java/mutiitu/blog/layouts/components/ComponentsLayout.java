package mutiitu.blog.layouts.components;

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
import java.util.HashMap;
import java.util.Map;

public class ComponentsLayout {

    @Inject
    CardUIComponent cardUIComponent;

    @Inject
    MarkdownUIComponent markdownUIComponent;

    @Inject
    MarkdownUIComponent markdownUIComponent2;


    public HttpResponse render() {
        try {
            // "/mutiitu/blog/layouts/home/HomeLayout.html"
            // TODO: GC
            PebbleEngine engine = new PebbleEngine.Builder().build();
            PebbleTemplate compiledTemplate = 
            //engine.getTemplate("mutiitu/blog/layouts/components/ComponentsLayout.html");
            engine.getTemplate("mutiitu/blog/layouts/components/ComponentsLayout.html");
            Map<String, Object> context = new HashMap<>();
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
