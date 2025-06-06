package mutiitu.blog.layouts.home;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.template.PebbleTemplate;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ContactFormLayout {


    public HttpResponse render() {
        try {
            PebbleEngine engine = new PebbleEngine.Builder().build();
            PebbleTemplate compiledTemplate = engine.getTemplate("mutiitu/blog/layouts/home/ContactForm.html");
            Map<String, Object> context = new HashMap<>();
            context.put("BASE_PATH", System.getenv().getOrDefault("BASE_PATH", "/"));

            Writer writer = new StringWriter();

            compiledTemplate.evaluate(writer, context);
            String output = writer.toString();
            return new HtmlResponse(output);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new StringResponse("output");
        }
    }
}
