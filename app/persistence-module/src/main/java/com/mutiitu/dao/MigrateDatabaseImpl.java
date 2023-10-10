package com.mutiitu.dao;

import com.google.inject.Inject;
import com.mutiitu.dao.impl.EmpoyeeDaoImpl;
import com.mutiitu.dao.impl.MigrateDaoImpl;
import com.mutiitu.persistence.SQLiteDB;

public class MigrateDatabaseImpl implements MigrateDatabase {

    public MigrateDatabaseImpl() {
    }

    @Inject
    SQLiteDB SQLiteDB;

    // @Inject
    // MigrateDao migrateDao;

    public void create() {
        var db = SQLiteDB;
        var tx = db.getTransactionManager();

        tx.required(
                () -> {
                    MigrateDao dao = new MigrateDaoImpl(db);
                    dao.create();
                });

    }

}
