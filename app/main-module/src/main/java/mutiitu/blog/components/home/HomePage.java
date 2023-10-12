package mutiitu.blog.components.home;

import java.util.List;
import com.google.inject.Inject;
import com.mutiitu.domain.BlogEntryModel;
import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

import mutiitu.blog.components.BlogEntryComponent;
import mutiitu.blog.components.Header3;

@Component(template = "homepage.html")
public class HomePage extends UIComponent {
    public String subtitle;

    @Inject
    public Header3 header;

    @Inject
    public BlogEntryComponent blogEntryComponent;

    public List<Integer> blogIds;
    public List<BlogEntryModel> blogEntries;

    public void init(String subtitle, List<Integer> blogIds, List<BlogEntryModel> blogEntries) {
        this.subtitle = subtitle;
        this.blogIds = blogIds;
        this.blogEntries = blogEntries;
    }
}
