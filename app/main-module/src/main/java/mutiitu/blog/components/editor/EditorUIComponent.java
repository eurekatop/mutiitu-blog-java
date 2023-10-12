package mutiitu.blog.components.editor;

import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

@Component(template = "EditorUIComponent.html")
public class EditorUIComponent extends UIComponent {
    public String document;

    public void init(String documentUIModel) {
        this.document = documentUIModel;
    }
}
