package com.mutiitu.persistence;

import java.util.UUID;
import javax.sql.DataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
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
public class DatabaseConfig implements Config {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private final UUID uuid = UUID.randomUUID();

    private final Dialect dialect;

    static HikariDataSource hikariDataSource;

    private final LocalTransactionDataSource dataSource;
    private final LocalTransactionManager transactionManager;

    private final DSLContext dslContext;

    public DatabaseConfig() {
        dialect = new MysqlDialect();

        if (DatabaseConfig.hikariDataSource == null) {
            DatabaseConfig.hikariDataSource = new HikariDataSource();
            
            // Get database URL from environment variable, defaulting to a fallback value if not set
            String jdbcUrl = System.getenv("JDBC_URL");
            if (jdbcUrl == null || jdbcUrl.isEmpty()) {
                jdbcUrl = "jdbc:mariadb://localhost:3306/test_mu";
            }
            DatabaseConfig.hikariDataSource.setJdbcUrl(jdbcUrl);          
            
            // Get database username from environment variable, defaulting to a fallback value if not set
            String username = System.getenv("DB_USERNAME");
            if (username == null || username.isEmpty()) {
                username = "xxxxxxxxxxxxxxxx";
            }
            DatabaseConfig.hikariDataSource.setUsername(username);

            // Get database password from environment variable, defaulting to a fallback value if not set
            String password = System.getenv("DB_PASSWORD");
            if (password == null || password.isEmpty()) {
                password = "xxxxxxxxxx.xxxxxxxxxxx";
            }
            DatabaseConfig.hikariDataSource.setPassword(password);
            
            DatabaseConfig.hikariDataSource.setMaximumPoolSize(20);

        }

        // DOMA
        dataSource = new LocalTransactionDataSource(DatabaseConfig.hikariDataSource);
        transactionManager = new LocalTransactionManager(
                dataSource.getLocalTransaction(getJdbcLogger(), TransactionIsolationLevel.READ_UNCOMMITTED));

        // JOOQ
         dslContext = DSL.using(hikariDataSource, SQLDialect.MYSQL);
         dslContext.settings().setMapConstructorParameterNames(true);

        logger.info(Thread.currentThread().toString());
        logger.info("################## DB !!!!!!!!!! " + uuid);

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
        return DatabaseConfig.hikariDataSource;
    }

    public LocalTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public DSLContext getDslContext() {
        return dslContext;
    }

}