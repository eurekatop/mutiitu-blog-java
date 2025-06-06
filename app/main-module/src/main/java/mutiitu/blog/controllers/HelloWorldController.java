package mutiitu.blog.controllers;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import io.javalin.Javalin;
import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import mutiitu.blog.components.Header;
import mutiitu.blog.components.Header2;
import mutiitu.blog.components.HeaderService;
import mutiitu.blog.services.BlogEntryService;

import com.mutiitu.annotations.Transactional;
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
public class HelloWorldController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private Javalin javalin;
    @Inject
    private Header2 header2;

    @Inject
    private HeaderService headerService;

    @Inject
    private BlogEntryService blogEntryService;

    private String template = """
            <html>
            <head>
                <title>{% block title %}My kakita2 Website{% endblock %}</title>
            </head>
            <body>
                {{ name }}
                {{ test.render() }}
                <div id="content">
                    {% block content %}{% endblock %}
                </div>
                <div id="footer">
                    {% block footer %}
                        Copyright 2018
                    {% endblock %}
                </div>
            </body>
            </html>
                """;

    @Path(Value = "/aa")
    @Method(Value = "GET")
    public HttpResponse aa(int id) {

        BlogEntryModel result = blogEntryService.GetBydId(id);

        return new JsonResponse(result);
    }

    @Path(Value = "/about")
    public void about(String name, String lastName, int age) {
        logger.info("Que me dices tu? " + name + " , " + lastName + "años: " + age);
        logger.info(javalin.toString());
        logger.info(headerService.toString());

        headerService.render();

    }

    @Path(Value = "/add")
    @Method(Value = "POST") // TODO: refactor
    public StringResponse add(String name) {
        logger.info("Que me dices? " + name);
        logger.info(javalin.toString());

        return new StringResponse("Hello World 1AA11111");
    }

    @Path(Value = "/addhtml")
    @Method(Value = "POST") // TODO: refactor
    public HttpResponse addhtml(String name) {
        logger.info("Que me dices? " + name);
        logger.info(javalin.toString());

        // templ, engine
        try {
            PebbleEngine engine = new PebbleEngine.Builder().build();
            // PebbleTemplate compiledTemplate = engine.getTemplate("templates/home2.html");
            // PebbleTemplate compiledTemplate = engine.getLiteralTemplate(template);

            PebbleTemplate compiledTemplate = engine.getTemplate("java/mutiitu/blog/components/addhtml.html");

            Map<String, Object> context = new HashMap<>();
            context.put("BASE_PATH", System.getenv().getOrDefault("BASE_PATH", "/"));

            var items = new ArrayList<String>();
            items.add("a ");
            items.add("b ");
            items.add("bfdjfdslfjldf  ");

            context.put("name", "Mitchell");

            context.put("items", items);
            context.put("test", header2);

            Writer writer = new StringWriter();

            compiledTemplate.evaluate(writer, context);
            String output = writer.toString();
            return new HtmlResponse(output);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("name", e);
        }

        return new HtmlResponse("kkk");

    }

    // @Override
    // public void bind() {
    // javalin.get("/hello-world", ctx -> {
    // ctx.render("templates/hello.html");
    // });
    // }

}
