package com.mutiitu.di;

import org.seasar.doma.Dao;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Scope;
import com.google.inject.Scopes;
import com.google.inject.matcher.Matchers;
import com.mutiitu.annotations.Transactional;
import com.mutiitu.core.ModelCrudDaoImpl;
import com.mutiitu.dao.MigrateDatabase;
import com.mutiitu.dao.MigrateDatabaseImpl;
import com.mutiitu.di.interceptors.TransactionalInterceptor;
import com.mutiitu.persistence.DatabaseConfig;
import com.mutiitu.persistence.ThreadLocalScope;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;


public class PersistenceModule extends AbstractModule {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());


    public static final Scope SQLiteDBScope = new ThreadLocalScope();

    @Override
    protected void configure() {
        bindAllDaosInPackage("com.mutiitu.dao");

        // bind(MigrateDao.class).in(Scopes.SINGLETON);
        bind(MigrateDatabase.class).to(MigrateDatabaseImpl.class).in(Scopes.SINGLETON);

        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transactional.class),
                new TransactionalInterceptor(getProvider(DatabaseConfig.class)));

    }

    private void bindAllDaosInPackage(String packageName) {
        
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackage(packageName) 
                .setScanners(
                        Scanners.SubTypes
                        ));

        var daoClasses = reflections.getSubTypesOf(ModelCrudDaoImpl.class);                        

        // Iterate over each DAO class and bind it to the module
        for (Class<?> daoClass : daoClasses) {
            logger.debug("Binding DAO class: " + daoClass.getName());
            bind(daoClass).in(SQLiteDBScope);
        }
    }
}
