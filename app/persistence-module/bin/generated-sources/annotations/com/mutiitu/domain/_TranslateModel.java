package com.mutiitu.domain;

/** */
@javax.annotation.processing.Generated(value = { "Doma", "2.54.0" }, date = "2023-10-03T20:59:55.427+0200")
@org.seasar.doma.EntityTypeImplementation
public final class _TranslateModel extends org.seasar.doma.jdbc.entity.AbstractEntityType<com.mutiitu.domain.TranslateModel> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("2.54.0");
    }

    private static final _TranslateModel __singleton = new _TranslateModel();

    private final org.seasar.doma.jdbc.entity.NamingType __namingType = null;

    private final java.util.function.Supplier<org.seasar.doma.jdbc.entity.NullEntityListener<com.mutiitu.domain.TranslateModel>> __listenerSupplier;

    private final boolean __immutable;

    private final String __catalogName;

    private final String __schemaName;

    private final String __tableName;

    private final boolean __isQuoteRequired;

    private final String __name;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.mutiitu.domain.TranslateModel, ?>> __idPropertyTypes;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.mutiitu.domain.TranslateModel, ?>> __entityPropertyTypes;

    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<com.mutiitu.domain.TranslateModel, ?>> __entityPropertyTypeMap;

    @SuppressWarnings("unused")
    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EmbeddedPropertyType<com.mutiitu.domain.TranslateModel, ?>> __embeddedPropertyTypeMap;

    private _TranslateModel() {
        __listenerSupplier = org.seasar.doma.internal.jdbc.entity.NullEntityListenerSuppliers.of();
        __immutable = false;
        __name = "TranslateModel";
        __catalogName = "";
        __schemaName = "";
        __tableName = "translate";
        __isQuoteRequired = false;
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.mutiitu.domain.TranslateModel, ?>> __idList = new java.util.ArrayList<>();
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.mutiitu.domain.TranslateModel, ?>> __list = new java.util.ArrayList<>(3);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<com.mutiitu.domain.TranslateModel, ?>> __map = new java.util.LinkedHashMap<>(3);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EmbeddedPropertyType<com.mutiitu.domain.TranslateModel, ?>> __embeddedMap = new java.util.LinkedHashMap<>(3);
        initializeMaps(__map, __embeddedMap);
        initializeIdList(__map, __idList);
        initializeList(__map, __list);
        __idPropertyTypes = java.util.Collections.unmodifiableList(__idList);
        __entityPropertyTypes = java.util.Collections.unmodifiableList(__list);
        __entityPropertyTypeMap = java.util.Collections.unmodifiableMap(__map);
        __embeddedPropertyTypeMap = java.util.Collections.unmodifiableMap(__embeddedMap);
    }

    private void initializeMaps(java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<com.mutiitu.domain.TranslateModel, ?>> __map, java.util.Map<String, org.seasar.doma.jdbc.entity.EmbeddedPropertyType<com.mutiitu.domain.TranslateModel, ?>> __embeddedMap) {
        __map.put("id", new org.seasar.doma.jdbc.entity.AssignedIdPropertyType<com.mutiitu.domain.TranslateModel, java.lang.String, java.lang.String>(com.mutiitu.domain.TranslateModel.class, org.seasar.doma.internal.jdbc.scalar.BasicScalarSuppliers.ofString(), "id", "", __namingType, false));
        __map.put("language", new org.seasar.doma.jdbc.entity.DefaultPropertyType<com.mutiitu.domain.TranslateModel, java.lang.String, java.lang.String>(com.mutiitu.domain.TranslateModel.class, org.seasar.doma.internal.jdbc.scalar.BasicScalarSuppliers.ofString(), "language", "", __namingType, true, true, false));
        __map.put("value", new org.seasar.doma.jdbc.entity.DefaultPropertyType<com.mutiitu.domain.TranslateModel, java.lang.String, java.lang.String>(com.mutiitu.domain.TranslateModel.class, org.seasar.doma.internal.jdbc.scalar.BasicScalarSuppliers.ofString(), "value", "", __namingType, true, true, false));
    }

    private void initializeIdList(java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<com.mutiitu.domain.TranslateModel, ?>> __map, java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.mutiitu.domain.TranslateModel, ?>> __idList) {
        __idList.add(__map.get("id"));
    }

    private void initializeList(java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<com.mutiitu.domain.TranslateModel, ?>> __map, java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.mutiitu.domain.TranslateModel, ?>> __list) {
        __list.addAll(__map.values());
    }

    @Override
    public org.seasar.doma.jdbc.entity.NamingType getNamingType() {
        return __namingType;
    }

    @Override
    public boolean isImmutable() {
        return __immutable;
    }

    @Override
    public String getName() {
        return __name;
    }

    @Override
    public String getCatalogName() {
        return __catalogName;
    }

    @Override
    public String getSchemaName() {
        return __schemaName;
    }

    @Override
    @Deprecated
    public String getTableName() {
        return getTableName(org.seasar.doma.internal.jdbc.entity.TableNames.namingFunction);
    }

    @Override
    public String getTableName(java.util.function.BiFunction<org.seasar.doma.jdbc.entity.NamingType, String, String> namingFunction) {
        if (__tableName.isEmpty()) {
            return namingFunction.apply(__namingType, __name);
        }
        return __tableName;
    }

    @Override
    public boolean isQuoteRequired() {
        return __isQuoteRequired;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preInsert(com.mutiitu.domain.TranslateModel entity, org.seasar.doma.jdbc.entity.PreInsertContext<com.mutiitu.domain.TranslateModel> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preUpdate(com.mutiitu.domain.TranslateModel entity, org.seasar.doma.jdbc.entity.PreUpdateContext<com.mutiitu.domain.TranslateModel> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preDelete(com.mutiitu.domain.TranslateModel entity, org.seasar.doma.jdbc.entity.PreDeleteContext<com.mutiitu.domain.TranslateModel> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preDelete(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postInsert(com.mutiitu.domain.TranslateModel entity, org.seasar.doma.jdbc.entity.PostInsertContext<com.mutiitu.domain.TranslateModel> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postUpdate(com.mutiitu.domain.TranslateModel entity, org.seasar.doma.jdbc.entity.PostUpdateContext<com.mutiitu.domain.TranslateModel> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postDelete(com.mutiitu.domain.TranslateModel entity, org.seasar.doma.jdbc.entity.PostDeleteContext<com.mutiitu.domain.TranslateModel> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postDelete(entity, context);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.mutiitu.domain.TranslateModel, ?>> getEntityPropertyTypes() {
        return __entityPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.EntityPropertyType<com.mutiitu.domain.TranslateModel, ?> getEntityPropertyType(String __name) {
        return __entityPropertyTypeMap.get(__name);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.mutiitu.domain.TranslateModel, ?>> getIdPropertyTypes() {
        return __idPropertyTypes;
    }

    @SuppressWarnings("unchecked")
    @Override
    public org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<com.mutiitu.domain.TranslateModel, ?, ?> getGeneratedIdPropertyType() {
        return (org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<com.mutiitu.domain.TranslateModel, ?, ?>)__entityPropertyTypeMap.get("null");
    }

    @SuppressWarnings("unchecked")
    @Override
    public org.seasar.doma.jdbc.entity.VersionPropertyType<com.mutiitu.domain.TranslateModel, ?, ?> getVersionPropertyType() {
        return (org.seasar.doma.jdbc.entity.VersionPropertyType<com.mutiitu.domain.TranslateModel, ?, ?>)__entityPropertyTypeMap.get("null");
    }

    @SuppressWarnings("unchecked")
    @Override
    public org.seasar.doma.jdbc.entity.TenantIdPropertyType<com.mutiitu.domain.TranslateModel, ?, ?> getTenantIdPropertyType() {
        return (org.seasar.doma.jdbc.entity.TenantIdPropertyType<com.mutiitu.domain.TranslateModel, ?, ?>)__entityPropertyTypeMap.get("null");
    }

    @Override
    public com.mutiitu.domain.TranslateModel newEntity(java.util.Map<String, org.seasar.doma.jdbc.entity.Property<com.mutiitu.domain.TranslateModel, ?>> __args) {
        com.mutiitu.domain.TranslateModel entity = new com.mutiitu.domain.TranslateModel();
        if (__args.get("id") != null) __args.get("id").save(entity);
        if (__args.get("language") != null) __args.get("language").save(entity);
        if (__args.get("value") != null) __args.get("value").save(entity);
        return entity;
    }

    @Override
    public Class<com.mutiitu.domain.TranslateModel> getEntityClass() {
        return com.mutiitu.domain.TranslateModel.class;
    }

    @Override
    public com.mutiitu.domain.TranslateModel getOriginalStates(com.mutiitu.domain.TranslateModel __entity) {
        return null;
    }

    @Override
    public void saveCurrentStates(com.mutiitu.domain.TranslateModel __entity) {
    }

    /**
     * @return the singleton
     */
    public static _TranslateModel getSingletonInternal() {
        return __singleton;
    }

    /**
     * @return the new instance
     */
    public static _TranslateModel newInstance() {
        return new _TranslateModel();
    }

}
