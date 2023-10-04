package mutiitu.blog.components.home;

//import com.google.inject.Inject;
import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;
import com.mutiitu.framework.core.ui.UIComponentFactory;

import mutiitu.blog.components.Header3;

@Component (
    template = "homepage.html"
)
public class HomePage extends UIComponent{
    public String subtitle;

    //@Inject
    //public Header3 header3 = new Header3();

    //public Header3 header3;

    public UIComponentFactory<Header3> header3 = new UIComponentFactory<Header3>(); 


    public void init(String subtitle){
        this.subtitle = subtitle;
    }

}
