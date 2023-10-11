package mutiitu.blog.components.card;

import com.google.gson.annotations.Expose;
import com.mutiitu.framework.core.annotations.Component;
import com.mutiitu.framework.core.ui.UIComponent;

import mutiitu.blog.components._model.CardUIModel;

@Component(template = "CardComponent.html")
public class CardUIComponent extends UIComponent {
    @Expose
    public CardUIModel card;

    public void init(CardUIModel card) {
        this.card = card;
    }
}
