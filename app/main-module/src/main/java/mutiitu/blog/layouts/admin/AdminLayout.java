package mutiitu.blog.layouts.admin;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import mutiitu.blog.components.admin.AdminHeaderComponent;
import mutiitu.blog.components.admin.core.ButtonActionComponent;
import mutiitu.blog.components.admin.pages.ConfigPageComponent;

public class AdminLayout {

    @Inject
    public AdminHeaderComponent header;
    @Inject
    public ConfigPageComponent configPageComponent;

    

    public HttpResponse render() {
        try {
            PebbleEngine engine = new PebbleEngine.Builder().build();
            PebbleTemplate compiledTemplate = engine.getTemplate("mutiitu/blog/layouts/admin/AdminLayout.html");
            Map<String, Object> context = new HashMap<>();
            context.put("BASE_PATH", System.getenv().getOrDefault("BASE_PATH", "/"));

            context.put("name", "Mitchell");
            context.put("header", header);
            context.put("configPage", configPageComponent);


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
