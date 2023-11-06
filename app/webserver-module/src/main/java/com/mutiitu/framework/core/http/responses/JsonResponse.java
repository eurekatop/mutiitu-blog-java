package com.mutiitu.framework.core.http.responses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    // public String toJsonString() {
    // try {
    //
    // GsonBuilder gsonBuilder = new GsonBuilder();
    // if (expose) {
    // gsonBuilder = gsonBuilder.excludeFieldsWithoutExposeAnnotation();
    // }
    //
    // Gson gson = gsonBuilder.create();
    //
    // var json = gson.toJson(data);
    //
    // return json;
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // return "{}";
    // }
    // }
}
