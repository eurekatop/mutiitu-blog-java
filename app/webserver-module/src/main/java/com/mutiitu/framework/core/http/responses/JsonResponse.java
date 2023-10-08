package com.mutiitu.framework.core.http.responses;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonResponse extends HttpResponse {
    public Object data;

    public JsonResponse(Object data) {
        super(HttpResponseType.Json);
        this.data = data;
    }

    public String toJsonString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
