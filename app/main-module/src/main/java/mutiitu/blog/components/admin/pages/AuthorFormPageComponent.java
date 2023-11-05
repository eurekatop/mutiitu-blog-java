package mutiitu.blog.components.admin.pages;

import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

@Component (
    template = "AuthorFormPageComponent.html"
)
public class AuthorFormPageComponent extends UIComponent{
    public AuthorFormPageComponent init(String title) {
        return this;
    }
}

