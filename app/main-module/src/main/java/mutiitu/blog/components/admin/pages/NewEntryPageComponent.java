package mutiitu.blog.components.admin.pages;

import com.google.inject.Inject;
import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

import mutiitu.blog.components.admin.core.ButtonActionComponent;


@Component (
    template = "NewEntryPageComponent.html"
)
public class NewEntryPageComponent extends UIComponent{
    @Inject
    public ButtonActionComponent buttonAction;

    public NewEntryPageComponent init(String title) {
        return this;
    }
}

