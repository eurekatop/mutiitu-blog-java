package com.mutiitu.framework.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import io.javalin.http.Context;

public class FormDataParser {

    public static <T> T parseFormAsClass(Context ctx, Class<T> dtoClass) {
        JsonObject jsonObject = new JsonObject();

        var formDataMap = ctx.formParamMap();

        for (var entry : formDataMap.entrySet()) {
            var fieldValue = entry.getValue().get(0);
            String fieldName = entry.getKey();

            jsonObject.addProperty(fieldName, fieldValue);
        }

        String json = jsonObject.toString();

        System.out.println(json);

        Gson gson = new GsonBuilder()
                .create();

        T result = gson.fromJson(json, dtoClass);

        return result;

    }

}
