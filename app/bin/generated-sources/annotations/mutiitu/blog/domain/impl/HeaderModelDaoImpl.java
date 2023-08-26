package mutiitu.blog.domain.impl;

/** */
@javax.annotation.processing.Generated(value = { "Doma", "2.54.0" }, date = "2023-08-26T12:55:54.839+0200")
@org.seasar.doma.DaoImplementation
public class HeaderModelDaoImpl implements mutiitu.blog.domain.HeaderModelDao, org.seasar.doma.jdbc.ConfigProvider {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("2.54.0");
    }

    private static final java.lang.reflect.Method __method0 = org.seasar.doma.internal.jdbc.dao.DaoImplSupport.getDeclaredMethod(mutiitu.blog.domain.HeaderModelDao.class, "selectById", java.lang.Integer.class);

    private static final java.lang.reflect.Method __method1 = org.seasar.doma.internal.jdbc.dao.DaoImplSupport.getDeclaredMethod(mutiitu.blog.domain.HeaderModelDao.class, "insert", mutiitu.blog.domain.HeaderModel.class);

    private final org.seasar.doma.internal.jdbc.dao.DaoImplSupport __support;

    /**
     * @param config the config
     */
    public HeaderModelDaoImpl(org.seasar.doma.jdbc.Config config) {
        __support = new org.seasar.doma.internal.jdbc.dao.DaoImplSupport(config);
    }

    @Override
    public org.seasar.doma.jdbc.Config getConfig() {
        return __support.getConfig();
    }

    @Override
    public mutiitu.blog.domain.HeaderModel selectById(java.lang.Integer id) {
        __support.entering("mutiitu.blog.domain.impl.HeaderModelDaoImpl", "selectById", id);
        try {
            org.seasar.doma.jdbc.query.SqlFileSelectQuery __query = __support.getQueryImplementors().createSqlFileSelectQuery(__method0);
            __query.setMethod(__method0);
            __query.setConfig(__support.getConfig());
            __query.setSqlFilePath("mutiitu.blog.domain.HeaderModelDao#selectById");
            __query.setEntityType(mutiitu.blog.domain._HeaderModel.getSingletonInternal());
            __query.addParameter("id", java.lang.Integer.class, id);
            __query.setCallerClassName("mutiitu.blog.domain.impl.HeaderModelDaoImpl");
            __query.setCallerMethodName("selectById");
            __query.setResultEnsured(false);
            __query.setResultMappingEnsured(false);
            __query.setFetchType(org.seasar.doma.FetchType.LAZY);
            __query.setQueryTimeout(-1);
            __query.setMaxRows(-1);
            __query.setFetchSize(-1);
            __query.setSqlLogType(org.seasar.doma.jdbc.SqlLogType.FORMATTED);
            __query.prepare();
            org.seasar.doma.jdbc.command.SelectCommand<mutiitu.blog.domain.HeaderModel> __command = __support.getCommandImplementors().createSelectCommand(__method0, __query, new org.seasar.doma.internal.jdbc.command.EntitySingleResultHandler<mutiitu.blog.domain.HeaderModel>(mutiitu.blog.domain._HeaderModel.getSingletonInternal()));
            mutiitu.blog.domain.HeaderModel __result = __command.execute();
            __query.complete();
            __support.exiting("mutiitu.blog.domain.impl.HeaderModelDaoImpl", "selectById", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            __support.throwing("mutiitu.blog.domain.impl.HeaderModelDaoImpl", "selectById", __e);
            throw __e;
        }
    }

    @Override
    public int insert(mutiitu.blog.domain.HeaderModel headerModel) {
        __support.entering("mutiitu.blog.domain.impl.HeaderModelDaoImpl", "insert", headerModel);
        try {
            if (headerModel == null) {
                throw new org.seasar.doma.DomaNullPointerException("headerModel");
            }
            org.seasar.doma.jdbc.query.AutoInsertQuery<mutiitu.blog.domain.HeaderModel> __query = __support.getQueryImplementors().createAutoInsertQuery(__method1, mutiitu.blog.domain._HeaderModel.getSingletonInternal());
            __query.setMethod(__method1);
            __query.setConfig(__support.getConfig());
            __query.setEntity(headerModel);
            __query.setCallerClassName("mutiitu.blog.domain.impl.HeaderModelDaoImpl");
            __query.setCallerMethodName("insert");
            __query.setQueryTimeout(-1);
            __query.setSqlLogType(org.seasar.doma.jdbc.SqlLogType.FORMATTED);
            __query.setNullExcluded(false);
            __query.setIncludedPropertyNames();
            __query.setExcludedPropertyNames();
            __query.prepare();
            org.seasar.doma.jdbc.command.InsertCommand __command = __support.getCommandImplementors().createInsertCommand(__method1, __query);
            int __result = __command.execute();
            __query.complete();
            __support.exiting("mutiitu.blog.domain.impl.HeaderModelDaoImpl", "insert", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            __support.throwing("mutiitu.blog.domain.impl.HeaderModelDaoImpl", "insert", __e);
            throw __e;
        }
    }

}
