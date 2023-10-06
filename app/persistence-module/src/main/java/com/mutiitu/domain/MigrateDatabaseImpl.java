package com.mutiitu.domain;
import com.mutiitu.domain.impl.MigrateDaoImpl;
import com.mutiitu.persistence.SQLiteDB;

public class  MigrateDatabaseImpl {

    public MigrateDatabaseImpl(){
    }

    public void create () {
      var db = SQLiteDB.singleton();
      var tx = db.getTransactionManager();
      tx.required(
        () -> {
            MigrateDao dao = new MigrateDaoImpl(db);
            dao.create();
        });

    }

}
