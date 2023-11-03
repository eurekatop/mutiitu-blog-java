package mutiitu.blog.controllers.rest;

import java.util.Map;

import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import mutiitu.blog.models.dto.CMSEntryInputDto;
import mutiitu.blog.services.BlogEntryService;
import com.mutiitu.annotations.Transactional;
import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;
import com.mutiitu.framework.utils.FormDataParser;

import io.javalin.validation.JavalinValidation;
import io.javalin.validation.ValidationError;

@Controller
public class CMSEntryController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private final BlogEntryService blogEntryService;

    @Inject
    public CMSEntryController(BlogEntryService blogEntryService) {
        this.blogEntryService = blogEntryService;
    }

    @Transactional
    @Path(Value = "/cms-entry/post")
    @Method(Value = "POST")
    public HttpResponse post() {

        //
        var i = new ValidationError<String>("kk", Map.of("lomit", 5), "ee");

        var r = ctx.formParamAsClass("title", String.class)
                .check(it -> {
                    System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    System.err.println(it);

                    return !(it.isBlank() && it.isEmpty());
                }, i)
                .get();

        // ctx.bodyValidator(CMSEntryInputDto.class)
        // .check(it -> it.getTitle().isEmpty(), "'to' has to be after 'from'")
        // .getOrThrow(
        // validationErrors -> {
        // // Aquí puedes manejar las excepciones generadas en la validación
        // // Por ejemplo, lanzar una excepción personalizada con detalles de los
        // // errores de validación
        // return new Exception();
        // });

        var data = FormDataParser.parseFormAsClass(ctx, CMSEntryInputDto.class);
        return new StringResponse(r);

        // var result = blogEntryService.Create(data);
        //
        // return new JsonResponse(result);
        //
        // var data = gson.fromJson(ctx.body(), BlogEntryInputDto.class);
        //
        //
        // var contentType = ctx.queryParam("content-type");
        // if (contentType == null) {
        // contentType = "html";
        // }
        //
        // var result = blogEntryService.GetBydId(id);
        //
        // switch (contentType) {
        // case "json":
        // return new JsonResponse(result);
        // default:
        // return new HtmlResponse(blogEntryComponent.init(result));
        // }

    }

}
