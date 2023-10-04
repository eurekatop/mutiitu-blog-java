package com.mutiitu.framework.core.http.responses;

import com.mutiitu.framework.core.ui.UIComponent;

public class HtmlResponse extends HttpResponse{
    public String data;


    public HtmlResponse(String data) {
        super(HttpResponseType.Html);
        this.data = data;
    }

    public HtmlResponse(UIComponent component) {
        super(HttpResponseType.Html);
        this.data = component.toString();
    }
}
