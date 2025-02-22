package com.mutiitu.framework.core.http.responses;

import com.google.gson.annotations.Expose;

public class JsonResponse extends HttpResponse {
    @Expose
    public Object data;

    private Boolean expose = false;

    public JsonResponse(Object data) {
        super(HttpResponseType.Json);
        this.data = data;
    }

    public JsonResponse(Object data, Boolean expose) {
        super(HttpResponseType.Json);
        this.data = data;
        this.expose = expose;
    }
}
