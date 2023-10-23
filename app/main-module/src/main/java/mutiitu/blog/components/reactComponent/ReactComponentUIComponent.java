package mutiitu.blog.components.reactComponent;

import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

@Component(template = "EditorUIComponent.html")
public class ReactComponentUIComponent extends UIComponent {
    public String document;

    public void init(String documentUIModel) {
        this.document = documentUIModel;
    }
}
