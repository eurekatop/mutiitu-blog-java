package mutiitu.blog.components.admin.core;

import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

@Component (
    template = "ButtonActionComponent.html"
)
public class ButtonActionComponent extends UIComponent{
    public String label;
    public String actionUrl;

    public ButtonActionComponent init(String label, String actionUrl) {
        this.label = label;
        this.actionUrl = actionUrl;
        return this;
    }
}
