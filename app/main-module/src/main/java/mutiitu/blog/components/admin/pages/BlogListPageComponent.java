package mutiitu.blog.components.admin.pages;

import java.util.ArrayList;
import java.util.List;

import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

import mutiitu.blog.models.dto.BlogEntryInputDto;

@Component (
    template = "BlogListPageComponent.html"
)
public class BlogListPageComponent extends UIComponent{

    public List<BlogEntryInputDto> blogs = new ArrayList<>();


    public BlogListPageComponent(List<BlogEntryInputDto> blogs) {
        this.blogs = blogs;
    }

    public BlogListPageComponent init(String title) {
        return this;
    }
}

