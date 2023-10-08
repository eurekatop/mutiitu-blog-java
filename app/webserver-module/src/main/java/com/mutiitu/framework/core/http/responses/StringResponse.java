package com.mutiitu.framework.core.http.responses;

import com.mutiitu.framework.core.ui.UIComponent;

public class StringResponse extends HttpResponse {
    public String data;

    public StringResponse(String data) {
        super(HttpResponseType.String);
        this.data = data;
    }

    public StringResponse(UIComponent data) {
        super(HttpResponseType.String);
        this.data = data.toString();
    }
}
