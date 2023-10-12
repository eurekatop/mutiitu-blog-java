package mutiitu.blog.components.markdown;

import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

@Component(template = "MarkdownUIComponent.html")
public class MarkdownUIComponent extends UIComponent {
    public String document;

    public void init(String documentUIModel) {
        this.document = documentUIModel;
    }
}
