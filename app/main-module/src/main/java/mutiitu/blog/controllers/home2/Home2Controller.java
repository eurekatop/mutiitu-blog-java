package mutiitu.blog.controllers.home2;
import org.slf4j.LoggerFactory;

import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.StringResponse;

public class Home2Controller extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());


    @Path(Value = "/home2")
    @Method(Value = "GET") // TODO: refactor
    public StringResponse index() {
        logger.info("Que me dices? tu"  );

        return new StringResponse("Hello World 1AA11111");
    }    
}
