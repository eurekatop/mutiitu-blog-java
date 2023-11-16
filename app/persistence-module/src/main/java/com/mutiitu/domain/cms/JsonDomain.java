package com.mutiitu.domain.cms;
import java.lang.reflect.Type;

import org.seasar.doma.Domain;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

@Domain(valueType = String.class)
public class JsonDomain<T>{

    @Expose
    private String json;


    @Expose
    public T value;


    public JsonDomain(String value) {
        Type fooType2 = new TypeToken <T> () {}.getType();
        var gson = new Gson();
        this.value = gson.fromJson(value, fooType2);

        this.json = value;
    }

    public String getValue() {
        return this.json;
    }

}