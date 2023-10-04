package com.mutiitu.framework.core.http.responses;

public class HttpResponse {
    public enum HttpResponseType {
        String,
        Html,
        Json
    }

    public HttpResponseType httpResponseType;

    public HttpResponse(HttpResponseType type) {
        this.httpResponseType = type;
    }

}
