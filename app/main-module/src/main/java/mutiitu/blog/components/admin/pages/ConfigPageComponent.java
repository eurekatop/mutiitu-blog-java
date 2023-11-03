package mutiitu.blog.components.admin.pages;

import com.google.inject.Inject;
import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

import mutiitu.blog.components.admin.core.ButtonActionComponent;


@Component (
    template = "ConfigPageComponent.html"
)
public class ConfigPageComponent extends UIComponent{
    @Inject
    public ButtonActionComponent buttonAction;

    public ConfigPageComponent init(String title) {
        //this.title = title;
        return this;
    }
}

