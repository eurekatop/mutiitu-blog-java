package com.mutiitu.persistence;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
// import org.seasar.doma.jdbc.dialect.SqliteDialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;


public class SQLiteDB implements Config {

    private static final SQLiteDB CONFIG = new SQLiteDB();

    private final Dialect dialect;

    private final LocalTransactionDataSource dataSource;

    private final LocalTransactionManager transactionManager;

    public SQLiteDB() {
        
//        dialect = new H2Dialect();
//
//        dataSource = new LocalTransactionDataSource(
//               "jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1", "sa", null);
//
//        transactionManager = new LocalTransactionManager(
//                dataSource.getLocalTransaction(getJdbcLogger()));


        dialect = new MysqlDialect();

        dataSource = new LocalTransactionDataSource(
               "jdbc:mysql://db4free.net:3306/test_mu", "user_mu", "yhyds6a7!");

        transactionManager = new LocalTransactionManager(
                dataSource.getLocalTransaction(getJdbcLogger()));



//        dialect = new SqliteDialect();
//
//        
//        dataSource = new LocalTransactionDataSource(
//               "jdbc:sqlite:sample.db", "sa", null);
//        
//
//        transactionManager = new LocalTransactionManager(
//                dataSource.getLocalTransaction(getJdbcLogger()));
    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    public LocalTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public static SQLiteDB singleton() {
        return CONFIG;
    }
}