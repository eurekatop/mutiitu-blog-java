package com.mutiitu.core;

import java.lang.reflect.Method;
import java.util.function.Function;

public class FieldSelector {
    
    public static <T, R> String getColumnName(Function<T, R> getter, Class<T> entityClass) {
        try {
            for (Method method : entityClass.getDeclaredMethods()) {
                if (getter.equals(method)) {  // Compare references
                    org.seasar.doma.Column columnAnnotation = method.getAnnotation(org.seasar.doma.Column.class);
                    return (columnAnnotation != null) ? columnAnnotation.name() : null;
                }
            }
            throw new IllegalArgumentException("Method reference does not match any known method.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract column name", e);
        }
    }
}


