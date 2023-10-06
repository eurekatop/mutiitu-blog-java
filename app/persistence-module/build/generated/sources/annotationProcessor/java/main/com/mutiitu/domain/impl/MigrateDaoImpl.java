package com.mutiitu.domain.impl;

/** */
@javax.annotation.processing.Generated(value = { "Doma", "2.54.0" }, date = "2023-10-06T06:34:36.157+0200")
@org.seasar.doma.DaoImplementation
public class MigrateDaoImpl implements com.mutiitu.domain.MigrateDao, org.seasar.doma.jdbc.ConfigProvider {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("2.54.0");
    }

    private static final java.lang.reflect.Method __method0 = org.seasar.doma.internal.jdbc.dao.DaoImplSupport.getDeclaredMethod(com.mutiitu.domain.MigrateDao.class, "create");

    private final org.seasar.doma.internal.jdbc.dao.DaoImplSupport __support;

    /**
     * @param config the config
     */
    public MigrateDaoImpl(org.seasar.doma.jdbc.Config config) {
        __support = new org.seasar.doma.internal.jdbc.dao.DaoImplSupport(config);
    }

    @Override
    public org.seasar.doma.jdbc.Config getConfig() {
        return __support.getConfig();
    }

    @Override
    public void create() {
        __support.entering("com.mutiitu.domain.impl.MigrateDaoImpl", "create");
        try {
            org.seasar.doma.jdbc.query.SqlFileScriptQuery __query = __support.getQueryImplementors().createSqlFileScriptQuery(__method0);
            __query.setMethod(__method0);
            __query.setConfig(__support.getConfig());
            __query.setScriptFilePath("com.mutiitu.domain.MigrateDao#create");
            __query.setCallerClassName("com.mutiitu.domain.impl.MigrateDaoImpl");
            __query.setCallerMethodName("create");
            __query.setBlockDelimiter("");
            __query.setHaltOnError(true);
            __query.setSqlLogType(org.seasar.doma.jdbc.SqlLogType.FORMATTED);
            __query.prepare();
            org.seasar.doma.jdbc.command.ScriptCommand __command = __support.getCommandImplementors().createScriptCommand(__method0, __query);
            __command.execute();
            __query.complete();
            __support.exiting("com.mutiitu.domain.impl.MigrateDaoImpl", "create", null);
        } catch (java.lang.RuntimeException __e) {
            __support.throwing("com.mutiitu.domain.impl.MigrateDaoImpl", "create", __e);
            throw __e;
        }
    }

}
