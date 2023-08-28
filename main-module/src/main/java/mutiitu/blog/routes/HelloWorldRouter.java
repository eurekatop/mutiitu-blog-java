package mutiitu.blog.routes;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import io.javalin.Javalin;
import io.javalin.http.Context;
import mutiitu.framework.core.JavalinController;
import mutiitu.framework.core.annotations.Controller;
import mutiitu.framework.core.annotations.Method;
import mutiitu.framework.core.annotations.Path;

@Controller
public class HelloWorldRouter extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private Javalin javalin;
    //private final MessageService service;

    public HelloWorldRouter() {
    }

    @Path(Value = "/home")
    public void aa () {
        System.out.println("me estan llamando desde core");
        System.out.println(javalin);
    }

    @Path(Value = "/about")
    public void about (String name, String lastName, int age) {
        System.out.println("Que me dices? " + name + " , " + lastName + "años: " + age);
        System.out.println(javalin);
    }

    @Path(Value = "/add")
    @Method(Value = "POST" ) //TODO: refactor
    //TODO; refactor, response
    public String add (String name) {
        System.out.println("Que me dices? " + name );
        System.out.println(javalin);

        //TODO; refactor, response
        return "Hello World 1AA11111";
    }



    // @Override
    // public void bind() {
    //     javalin.get("/hello-world", ctx -> {
    //         ctx.render("templates/hello.html");
    //     });
    // }



}
    
