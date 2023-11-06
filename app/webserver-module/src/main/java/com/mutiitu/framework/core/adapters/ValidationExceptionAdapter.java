package com.mutiitu.framework.core.adapters;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import io.javalin.validation.ValidationException;
import kotlin.NotImplementedError;

public class ValidationExceptionAdapter extends TypeAdapter<ValidationException> { 
   @Override 
   public ValidationException read(JsonReader reader) throws IOException { 
        throw new NotImplementedError();
   } 
   
   @Override 
   public void write(JsonWriter writer, ValidationException validationException) throws IOException { 
          Gson gson = new Gson();
          var errors = gson.toJson(validationException.getErrors());
          writer.jsonValue(errors);
   } 
}