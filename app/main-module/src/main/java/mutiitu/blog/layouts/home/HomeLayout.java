package mutiitu.blog.layouts.home;

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

public class HomeLayout {

    @Inject
    CardUIComponent cardUIComponent;

    @Inject
    MarkdownUIComponent markdownUIComponent;

    public HttpResponse render() {
        try {
            // "/mutiitu/blog/layouts/home/HomeLayout.html"
            // TODO: GC
            PebbleEngine engine = new PebbleEngine.Builder().build();
            PebbleTemplate compiledTemplate = engine.getTemplate("mutiitu/blog/layouts/home/HomeLayout.html");
            Map<String, Object> context = new HashMap<>();

            var items = new ArrayList<String>();
            items.add("a ");
            items.add("b ");
            items.add("bfdjfdslfjldf  ");

            context.put("name", "Mitchell");

            context.put("items", items);
            context.put("card", cardUIComponent);
            context.put("markdown", markdownUIComponent);
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
            # Cómo Redactar Contenido con Nudos de Enlace

            Los nudos de enlace son elementos clave en la redacción de contenido técnico, ya que permiten al lector navegar de manera eficiente a través de información relacionada o proporcionar acceso a recursos externos. Aquí te mostramos algunas pautas para redactar contenido efectivo utilizando nudos de enlace.

            ## 1. Identifica tus Nudos de Enlace

            Antes de comenzar a redactar, identifica los puntos clave en tu contenido donde los lectores pueden beneficiarse de información adicional. Estos puntos pueden incluir definiciones, ejemplos, referencias a documentos técnicos o enlaces a recursos relacionados.

            ## 2. Usa una Sintaxis Clara

            Asegúrate de que la sintaxis para crear un nudo de enlace sea clara y consistente. En Markdown, puedes crear un enlace de la siguiente manera:

            ```markdown
            [Texto del enlace](URL)
               """;

}
