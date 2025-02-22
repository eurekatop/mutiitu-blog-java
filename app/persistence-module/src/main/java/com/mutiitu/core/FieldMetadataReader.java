package com.mutiitu.core;

import org.seasar.doma.Column;
import org.seasar.doma.Id;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FieldMetadataReader {

    public static List<FieldMetadata> getFieldMetadata(Class<?> entityClass) {
    
        Field[] fields = entityClass.getDeclaredFields();
    
        List<FieldMetadata> metadataList = new ArrayList<>();
    
        for (Field field : fields) {
            String fieldName = field.getName();
            Class<?> fieldType = field.getType();
    
            Column columnAnnotation = field.getAnnotation(Column.class);
            Id idAnnotation = field.getAnnotation(Id.class);

            String columnName = 
                (idAnnotation != null) ? "ID" : (columnAnnotation != null) ? columnAnnotation.name() : "N/A";

            metadataList.add(new FieldMetadata(fieldName, fieldType, columnName));
        }
    
        return metadataList;
    }


}