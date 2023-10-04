package mutiitu.blog.components;

import org.slf4j.LoggerFactory;

import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

@Component (
    template = "home2.html"
)
public class Header3 extends UIComponent{
    public String subtitle;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());


    public UIComponent init(String subtitle){
        logger.info("public void init(String subtitle){");        
        logger.info(subtitle);        
        this.subtitle = subtitle;
        return this;
    }



}
