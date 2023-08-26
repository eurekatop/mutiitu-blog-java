package mutiitu.blog.domain;

/** */
@javax.annotation.processing.Generated(value = { "Doma", "2.54.0" }, date = "2023-08-26T12:57:22.483+0200")
@org.seasar.doma.EntityTypeImplementation
public final class _HeaderModel extends org.seasar.doma.jdbc.entity.AbstractEntityType<mutiitu.blog.domain.HeaderModel> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("2.54.0");
    }

    private static final _HeaderModel __singleton = new _HeaderModel();

    private final org.seasar.doma.jdbc.entity.NamingType __namingType = null;

    private final org.seasar.doma.jdbc.id.BuiltinIdentityIdGenerator __idGenerator = new org.seasar.doma.jdbc.id.BuiltinIdentityIdGenerator();

    private final java.util.function.Supplier<org.seasar.doma.jdbc.entity.NullEntityListener<mutiitu.blog.domain.HeaderModel>> __listenerSupplier;

    private final boolean __immutable;

    private final String __catalogName;

    private final String __schemaName;

    private final String __tableName;

    private final boolean __isQuoteRequired;

    private final String __name;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<mutiitu.blog.domain.HeaderModel, ?>> __idPropertyTypes;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<mutiitu.blog.domain.HeaderModel, ?>> __entityPropertyTypes;

    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<mutiitu.blog.domain.HeaderModel, ?>> __entityPropertyTypeMap;

    @SuppressWarnings("unused")
    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EmbeddedPropertyType<mutiitu.blog.domain.HeaderModel, ?>> __embeddedPropertyTypeMap;

    private _HeaderModel() {
        __listenerSupplier = org.seasar.doma.internal.jdbc.entity.NullEntityListenerSuppliers.of();
        __immutable = false;
        __name = "HeaderModel";
        __catalogName = "";
        __schemaName = "";
        __tableName = "Header";
        __isQuoteRequired = false;
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<mutiitu.blog.domain.HeaderModel, ?>> __idList = new java.util.ArrayList<>();
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<mutiitu.blog.domain.HeaderModel, ?>> __list = new java.util.ArrayList<>(2);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<mutiitu.blog.domain.HeaderModel, ?>> __map = new java.util.LinkedHashMap<>(2);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EmbeddedPropertyType<mutiitu.blog.domain.HeaderModel, ?>> __embeddedMap = new java.util.LinkedHashMap<>(2);
        initializeMaps(__map, __embeddedMap);
        initializeIdList(__map, __idList);
        initializeList(__map, __list);
        __idPropertyTypes = java.util.Collections.unmodifiableList(__idList);
        __entityPropertyTypes = java.util.Collections.unmodifiableList(__list);
        __entityPropertyTypeMap = java.util.Collections.unmodifiableMap(__map);
        __embeddedPropertyTypeMap = java.util.Collections.unmodifiableMap(__embeddedMap);
    }

    private void initializeMaps(java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<mutiitu.blog.domain.HeaderModel, ?>> __map, java.util.Map<String, org.seasar.doma.jdbc.entity.EmbeddedPropertyType<mutiitu.blog.domain.HeaderModel, ?>> __embeddedMap) {
        __map.put("id", new org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<mutiitu.blog.domain.HeaderModel, java.lang.Integer, java.lang.Integer>(mutiitu.blog.domain.HeaderModel.class, org.seasar.doma.internal.jdbc.scalar.BasicScalarSuppliers.ofInteger(), "id", "", __namingType, false, __idGenerator));
        __map.put("title", new org.seasar.doma.jdbc.entity.DefaultPropertyType<mutiitu.blog.domain.HeaderModel, java.lang.String, java.lang.String>(mutiitu.blog.domain.HeaderModel.class, org.seasar.doma.internal.jdbc.scalar.BasicScalarSuppliers.ofString(), "title", "TITLE", __namingType, true, true, false));
    }

    private void initializeIdList(java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<mutiitu.blog.domain.HeaderModel, ?>> __map, java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<mutiitu.blog.domain.HeaderModel, ?>> __idList) {
        __idList.add(__map.get("id"));
    }

    private void initializeList(java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<mutiitu.blog.domain.HeaderModel, ?>> __map, java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<mutiitu.blog.domain.HeaderModel, ?>> __list) {
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
    public void preInsert(mutiitu.blog.domain.HeaderModel entity, org.seasar.doma.jdbc.entity.PreInsertContext<mutiitu.blog.domain.HeaderModel> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preUpdate(mutiitu.blog.domain.HeaderModel entity, org.seasar.doma.jdbc.entity.PreUpdateContext<mutiitu.blog.domain.HeaderModel> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preDelete(mutiitu.blog.domain.HeaderModel entity, org.seasar.doma.jdbc.entity.PreDeleteContext<mutiitu.blog.domain.HeaderModel> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preDelete(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postInsert(mutiitu.blog.domain.HeaderModel entity, org.seasar.doma.jdbc.entity.PostInsertContext<mutiitu.blog.domain.HeaderModel> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postUpdate(mutiitu.blog.domain.HeaderModel entity, org.seasar.doma.jdbc.entity.PostUpdateContext<mutiitu.blog.domain.HeaderModel> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postDelete(mutiitu.blog.domain.HeaderModel entity, org.seasar.doma.jdbc.entity.PostDeleteContext<mutiitu.blog.domain.HeaderModel> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postDelete(entity, context);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<mutiitu.blog.domain.HeaderModel, ?>> getEntityPropertyTypes() {
        return __entityPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.EntityPropertyType<mutiitu.blog.domain.HeaderModel, ?> getEntityPropertyType(String __name) {
        return __entityPropertyTypeMap.get(__name);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<mutiitu.blog.domain.HeaderModel, ?>> getIdPropertyTypes() {
        return __idPropertyTypes;
    }

    @SuppressWarnings("unchecked")
    @Override
    public org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<mutiitu.blog.domain.HeaderModel, ?, ?> getGeneratedIdPropertyType() {
        return (org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<mutiitu.blog.domain.HeaderModel, ?, ?>)__entityPropertyTypeMap.get("id");
    }

    @SuppressWarnings("unchecked")
    @Override
    public org.seasar.doma.jdbc.entity.VersionPropertyType<mutiitu.blog.domain.HeaderModel, ?, ?> getVersionPropertyType() {
        return (org.seasar.doma.jdbc.entity.VersionPropertyType<mutiitu.blog.domain.HeaderModel, ?, ?>)__entityPropertyTypeMap.get("null");
    }

    @SuppressWarnings("unchecked")
    @Override
    public org.seasar.doma.jdbc.entity.TenantIdPropertyType<mutiitu.blog.domain.HeaderModel, ?, ?> getTenantIdPropertyType() {
        return (org.seasar.doma.jdbc.entity.TenantIdPropertyType<mutiitu.blog.domain.HeaderModel, ?, ?>)__entityPropertyTypeMap.get("null");
    }

    @Override
    public mutiitu.blog.domain.HeaderModel newEntity(java.util.Map<String, org.seasar.doma.jdbc.entity.Property<mutiitu.blog.domain.HeaderModel, ?>> __args) {
        mutiitu.blog.domain.HeaderModel entity = new mutiitu.blog.domain.HeaderModel();
        if (__args.get("id") != null) __args.get("id").save(entity);
        if (__args.get("title") != null) __args.get("title").save(entity);
        return entity;
    }

    @Override
    public Class<mutiitu.blog.domain.HeaderModel> getEntityClass() {
        return mutiitu.blog.domain.HeaderModel.class;
    }

    @Override
    public mutiitu.blog.domain.HeaderModel getOriginalStates(mutiitu.blog.domain.HeaderModel __entity) {
        return null;
    }

    @Override
    public void saveCurrentStates(mutiitu.blog.domain.HeaderModel __entity) {
    }

    /**
     * @return the singleton
     */
    public static _HeaderModel getSingletonInternal() {
        return __singleton;
    }

    /**
     * @return the new instance
     */
    public static _HeaderModel newInstance() {
        return new _HeaderModel();
    }

}
