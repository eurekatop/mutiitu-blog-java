package mutiitu.blog.components.markdownEditor;

import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

@Component(template = "EditorUIComponent.html")
public class MarkdownEditorUIComponent extends UIComponent {
    public String document;

    public void init(String documentUIModel) {
        this.document = documentUIModel;
    }
}
