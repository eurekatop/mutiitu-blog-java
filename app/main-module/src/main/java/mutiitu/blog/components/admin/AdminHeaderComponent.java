package mutiitu.blog.components.admin;
import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

@Component (
    template = "AdminHeaderComponent.html"
)
public class AdminHeaderComponent extends UIComponent{
    public String title;

    public AdminHeaderComponent init(String title) {
        this.title = title;
        return this;
    }
}
