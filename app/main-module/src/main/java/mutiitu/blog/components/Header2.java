package mutiitu.blog.components;

import com.google.inject.Inject;
import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

@Component (
    template = "home2.html"
)
public class Header2 extends UIComponent{
    public String title;

    @Inject
    public Header3 header3;

    public Header2 build(String title) {
        this.title = title;
        this.header3.init("title"); 
        return this;
    }
}
