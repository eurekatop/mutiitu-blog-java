package com.mutiitu.core;

public class FieldMetadata {
    private final String fieldName;
    private final Class<?> fieldType;
    private final String columnName;

    public FieldMetadata(String fieldName, Class<?> fieldType, String columnName) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.columnName = columnName;
    }

    // Getters
    public String getFieldName() {
        return fieldName;
    }

    public Class<?> getFieldType() {
        return fieldType;
    }

    public String getColumnName() {
        return columnName;
    }

    @Override
    public String toString() {
        return "FieldMetadata{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldType=" + fieldType +
                ", columnName='" + columnName + '\'' +
                '}';
    }
}