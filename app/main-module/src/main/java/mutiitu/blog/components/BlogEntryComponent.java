package mutiitu.blog.components;

import com.mutiitu.domain.BlogEntryModel;
import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

// TODO: config override convention
@Component(template = "BlogEntryComponent.html")
public class BlogEntryComponent extends UIComponent {
    public BlogEntryModel blogEntryModel;

    public UIComponent init(BlogEntryModel blogEntryModel) {
        this.blogEntryModel = blogEntryModel;
        return this;
    }
}
