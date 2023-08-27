package com.mutiitu.domain;

/** */
@javax.annotation.processing.Generated(value = { "Doma", "2.54.0" }, date = "2023-08-27T17:56:07.945+0200")
public final class HeaderModel_ implements org.seasar.doma.jdbc.criteria.metamodel.EntityMetamodel<com.mutiitu.domain.HeaderModel> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("2.54.0");
    }

    private final String __qualifiedTableName;

    private final com.mutiitu.domain._HeaderModel __entityType = com.mutiitu.domain._HeaderModel.getSingletonInternal();

    private final java.util.List<org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<?>> __allPropertyMetamodels;

    public final org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<java.lang.Integer> id = new org.seasar.doma.jdbc.criteria.metamodel.DefaultPropertyMetamodel<java.lang.Integer>(java.lang.Integer.class, __entityType, "id");

    public final org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<java.lang.String> title = new org.seasar.doma.jdbc.criteria.metamodel.DefaultPropertyMetamodel<java.lang.String>(java.lang.String.class, __entityType, "title");

    public HeaderModel_() {
        this("");
    }

    public HeaderModel_(String qualifiedTableName) {
        this.__qualifiedTableName = java.util.Objects.requireNonNull(qualifiedTableName);
        java.util.ArrayList<org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<?>> __list = new java.util.ArrayList<>(2);
        __list.add(id);
        __list.add(title);
        __allPropertyMetamodels = java.util.Collections.unmodifiableList(__list);
    }

    @Override
    public org.seasar.doma.jdbc.entity.EntityType<com.mutiitu.domain.HeaderModel> asType() {
        return __qualifiedTableName.isEmpty() ? __entityType : new org.seasar.doma.jdbc.criteria.metamodel.EntityTypeProxy<>(__entityType, __qualifiedTableName);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<?>> allPropertyMetamodels() {
        return __allPropertyMetamodels;
    }

}
