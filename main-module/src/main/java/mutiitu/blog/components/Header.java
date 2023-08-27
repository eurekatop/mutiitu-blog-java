package mutiitu.blog.components;

import com.google.inject.Inject;

public class Header {
    @Inject
    HeaderService headerService;

    public Header() {
    }

    public String render() {
        return headerService.render();
    }

}