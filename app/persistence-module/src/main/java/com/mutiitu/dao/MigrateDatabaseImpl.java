package com.mutiitu.dao;

import com.google.inject.Inject;
import com.mutiitu.dao.impl.MigrateDaoImpl;
import com.mutiitu.persistence.SQLiteDB;

public class MigrateDatabaseImpl {

    public MigrateDatabaseImpl() {
    }

    @Inject
    SQLiteDB SQLiteDB;

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
