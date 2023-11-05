package mutiitu.blog.components.admin.pages;

import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

import mutiitu.blog.models.dto.BlogEntryInputDto;

@Component (
    template = "BlogFormPageComponent.html"
)
public class BlogFormPageComponent extends UIComponent{

    public BlogEntryInputDto blog;

    public BlogFormPageComponent() {
        super();
        var blog = new BlogEntryInputDto();
        blog.id =0;
        this.blog = blog;
    }

    public BlogFormPageComponent(BlogEntryInputDto blog) {
        super();
        this.blog = blog;
    }
}

