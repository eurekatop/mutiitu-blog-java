package mutiitu.blog.components.admin.pages;

import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

@Component (
    template = "BlogFormPageComponent.html"
)
public class BlogFormPageComponent extends UIComponent{
    public BlogFormPageComponent init(String title) {
        return this;
    }
}

