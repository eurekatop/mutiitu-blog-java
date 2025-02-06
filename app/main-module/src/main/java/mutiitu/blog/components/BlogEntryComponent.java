package mutiitu.blog.components;

//import com.mutiitu.domain.BlogEntryModel;
import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

import mutiitu.blog.models.dto.BlogEntryInputDto;

// TODO: config override convention
@Component(template = "BlogEntryComponent.html")
public class BlogEntryComponent extends UIComponent {
    public BlogEntryInputDto blogEntryModel;

    public UIComponent init(BlogEntryInputDto blogEntryModel) {
        this.blogEntryModel = blogEntryModel;
        return this;
    }
}
