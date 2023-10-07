package com.mutiitu.persistence;

import javax.sql.DataSource;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;


public class SQLiteDB implements Config {

    private static final SQLiteDB CONFIG = new SQLiteDB();

    private final Dialect dialect;

    private final LocalTransactionDataSource dataSource;

    private final LocalTransactionManager transactionManager;

    public SQLiteDB() {
        dialect = new MysqlDialect();

        dataSource = new LocalTransactionDataSource(
               "jdbc:mariadb://mariadb:3306/mydatabase", "root", "mypassword");

        transactionManager = new LocalTransactionManager(
                dataSource.getLocalTransaction(getJdbcLogger()));
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