package mutiitu.blog.components.infinitescroll;

import com.google.gson.annotations.Expose;
import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

import mutiitu.blog.components._model.AuthorUIModel;
import mutiitu.blog.components._model.BlogPostUIModel;

@Component(template = "InfiniteScrollUIComponent")
public class InfiniteScrollUIComponent extends UIComponent {
    @Expose
    public BlogPostUIModel blog;

    @Expose
    public AuthorUIModel author;

    public void init(BlogPostUIModel blog, AuthorUIModel author) {
        this.blog = blog;
        this.author = author;
    }
}
