package com.mutiitu.persistence;

import java.sql.SQLException;
import java.util.UUID;
import javax.sql.DataSource;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionIsolationLevel;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;
import com.zaxxer.hikari.HikariDataSource;

@Singleton
public class SQLiteDB implements Config {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private final UUID uuid = UUID.randomUUID();

    private final Dialect dialect;

    static HikariDataSource hikariDataSource;

    private final LocalTransactionDataSource dataSource;
    // private final MTLocalTransactionDataSource dataSource;

    private final LocalTransactionManager transactionManager;

    public SQLiteDB() {
        dialect = new MysqlDialect();

        // dataSource = new LocalTransactionDataSource(
        // "jdbc:mariadb://mariadb:3306/test_mu", "root", "mypassword");

        if (SQLiteDB.hikariDataSource == null) {
            SQLiteDB.hikariDataSource = new HikariDataSource();
            // SQLiteDB.hikariDataSource.setJdbcUrl("jdbc:mariadb://mariadb:3306/test_mu");
            // SQLiteDB.hikariDataSource.setJdbcUrl("jdbc:mariadb://10.0.0.2:3306/test_mu");
            SQLiteDB.hikariDataSource.setJdbcUrl("jdbc:mariadb://localhost:3306/test_mu");
            SQLiteDB.hikariDataSource.setUsername("root");
            SQLiteDB.hikariDataSource.setPassword("mypassword");
            SQLiteDB.hikariDataSource.setMaximumPoolSize(20);

        }

        dataSource = new LocalTransactionDataSource(SQLiteDB.hikariDataSource);

        transactionManager = new LocalTransactionManager(
                dataSource.getLocalTransaction(getJdbcLogger(), TransactionIsolationLevel.READ_UNCOMMITTED));

        logger.info(Thread.currentThread().toString());
        logger.info("################## SQLiteDB() !!!!!!!!!! CREATED " + uuid);

    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    public DataSource getPoolDataSource() {
        return SQLiteDB.hikariDataSource;
    }

    public LocalTransactionManager getTransactionManager() {
        return transactionManager;
    }

}