package com.mutiitu.persistence;

import java.sql.SQLException;
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
public class SQLiteDB implements Config {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private final UUID uuid = UUID.randomUUID();

    private final Dialect dialect;

    static HikariDataSource hikariDataSource;

    private final LocalTransactionDataSource dataSource;
    private final LocalTransactionManager transactionManager;

    private final DSLContext dslContext;

    public SQLiteDB() {
        dialect = new MysqlDialect();

        // dataSource = new LocalTransactionDataSource(
        // "jdbc:mariadb://mariadb:3306/test_mu", "root", "mypassword");

        if (SQLiteDB.hikariDataSource == null) {
            SQLiteDB.hikariDataSource = new HikariDataSource();
            // SQLiteDB.hikariDataSource.setJdbcUrl("jdbc:mariadb://mariadb:3306/test_mu");
            
            // inside muttitu 
            // SQLiteDB.hikariDataSource.setJdbcUrl("jdbc:mariadb://10.0.0.2:3306/test_mu");

            
            
            // Get database URL from environment variable, defaulting to a fallback value if not set
            String jdbcUrl = System.getenv("DB_URL");
            if (jdbcUrl == null || jdbcUrl.isEmpty()) {
                jdbcUrl = "jdbc:mariadb://localhost:3306/test_mu";
            }
            SQLiteDB.hikariDataSource.setJdbcUrl(jdbcUrl);          
            
            // Get database username from environment variable, defaulting to a fallback value if not set
            String username = System.getenv("DB_USERNAME");
            if (username == null || username.isEmpty()) {
                username = "root";
            }
            SQLiteDB.hikariDataSource.setUsername(username);

            // Get database password from environment variable, defaulting to a fallback value if not set
            String password = System.getenv("DB_PASSWORD");
            if (password == null || password.isEmpty()) {
                password = "lampara.magica";
            }
            SQLiteDB.hikariDataSource.setPassword(password);
            
            ////SQLiteDB.hikariDataSource.setJdbcUrl("jdbc:mariadb://localhost:3306/test_mu");

            // inside docker
            SQLiteDB.hikariDataSource.setJdbcUrl("jdbc:mariadb://172.26.208.1:3306/test_mu"); 

            // inside local host
            SQLiteDB.hikariDataSource.setJdbcUrl("jdbc:mariadb://192.168.1.134:3306/test_mu"); 


            //SQLiteDB.hikariDataSource.setUsername("root");
            //SQLiteDB.hikariDataSource.setPassword("lampara.magica");

            // inside local host
            SQLiteDB.hikariDataSource.setJdbcUrl("jdbc:mariadb://192.168.1.134:3306/test_mu"); 



            SQLiteDB.hikariDataSource.setMaximumPoolSize(20);

        }

        // DOMA
        dataSource = new LocalTransactionDataSource(SQLiteDB.hikariDataSource);
        transactionManager = new LocalTransactionManager(
                dataSource.getLocalTransaction(getJdbcLogger(), TransactionIsolationLevel.READ_UNCOMMITTED));

        // JOOQ
         dslContext = DSL.using(hikariDataSource, SQLDialect.MARIADB);
        

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

    public DSLContext getDslContext() {
        return dslContext;
    }

}