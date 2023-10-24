package com.mutiitu.di;

import org.h2.mvstore.tx.Transaction;

import com.google.inject.AbstractModule;
import com.google.inject.Scope;
import com.google.inject.Scopes;
import com.google.inject.matcher.Matchers;
import com.mutiitu.annotations.Transactional;
import com.mutiitu.dao.BlogEntryDao;
import com.mutiitu.dao.ContactMeDao;
import com.mutiitu.dao.MigrateDao;
import com.mutiitu.dao.MigrateDatabase;
import com.mutiitu.dao.MigrateDatabaseImpl;
import com.mutiitu.di.interceptors.TransactionalInterceptor;
import com.mutiitu.persistence.SQLiteDB;
import com.mutiitu.persistence.SQLiteDBScope;

public class PersistenceModule extends AbstractModule {

    // TODO: refactor name
    public static final Scope SQLiteDBScope = new SQLiteDBScope();

    @Override
    protected void configure() {
        bind(SQLiteDB.class).in(SQLiteDBScope);
        // bind(SQLiteDB.class).in(Scopes.SINGLETON);
        bind(BlogEntryDao.class).in(SQLiteDBScope);
        bind(ContactMeDao.class).in(SQLiteDBScope);


        // bind(MigrateDao.class).in(Scopes.SINGLETON);
        bind(MigrateDatabase.class).to(MigrateDatabaseImpl.class).in(Scopes.SINGLETON);

        // interceptors
        // TransactionalInterceptor transactionalInterceptor = new
        // TransactionalInterceptor();
        // requestInjection(transactionalInterceptor);
        // bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transactional.class),
        // transactionalInterceptor);

        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transactional.class),
                new TransactionalInterceptor(getProvider(SQLiteDB.class)));

    }
}
