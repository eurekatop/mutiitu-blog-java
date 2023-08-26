package mutiitu.blog.core.persistence;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

public class SQLiteDB implements Config {

    private static final SQLiteDB CONFIG = new SQLiteDB();

    private final Dialect dialect;

    private final LocalTransactionDataSource dataSource;

    private final TransactionManager transactionManager;

    public SQLiteDB() {
        dialect = new H2Dialect();
        // dataSource = new LocalTransactionDataSource(
        //        "jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1", "sa", null);
        
        dataSource = new LocalTransactionDataSource(
               "jdbc:sqlite:sample.db", "sa", null);
        

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

    @Override
    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public static SQLiteDB singleton() {
        return CONFIG;
    }
}