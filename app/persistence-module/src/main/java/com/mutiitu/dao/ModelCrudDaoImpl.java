package com.mutiitu.dao;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;
import org.seasar.doma.jdbc.criteria.declaration.WhereDeclaration;
import org.seasar.doma.jdbc.criteria.metamodel.EntityMetamodel;
import org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.mutiitu.persistence.BaseModel;
import com.mutiitu.persistence.SQLiteDB;

public class ModelCrudDaoImpl<T extends BaseModel, T1 extends EntityMetamodel<T>> implements ModelCrudDao<T> {
    private final int THREAD_POOL = 40;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL);

    SQLiteDB SQLiteDB;

    protected Entityql eql;
    NativeSql nql;
    protected LocalTransactionManager tx;
    protected T1 t__; // TODO: model

    public ModelCrudDaoImpl(T1 doma_, SQLiteDB SQLiteDB) {
        tx = SQLiteDB.getTransactionManager();
        eql = new Entityql(SQLiteDB);
        nql = new NativeSql(SQLiteDB);
        t__ = doma_;
        this.SQLiteDB = SQLiteDB;
    }

    public ModelCrudDaoImpl(T1 doma_) {
        tx = SQLiteDB.getTransactionManager();
        eql = new Entityql(SQLiteDB);
        nql = new NativeSql(SQLiteDB);
        t__ = doma_;
    }

    public CompletableFuture<Void> insertAsync(T model) {
        return CompletableFuture.runAsync(() -> {
            try {
                insert(model);
            } catch (Exception ex) {
                logger.error("Error on insertAsync", ex);
                throw ex;
            }
        }, executor);
    }

    protected void txBegin() {
        tx.getTransaction().begin();
    }

    @Override
    public void insert(T model) {
        try {
            // var txx = tx.getTransaction();
            // txx.begin();
            // var r = eql.insert(t__, model).execute();
            // txx.commit();

            // tx.required(
            // () -> {
            eql.insert(t__, model).execute().getEntity();
            // });

        } catch (Exception e) {
            logger.error("Error on insert", e);
            throw e;
        }
    }

    @Override
    public CompletableFuture<Void> updateAsync(T model) {
        return CompletableFuture.runAsync(() -> {
            try {
                update(model);
            } catch (Exception ex) {
                logger.error("Error on updateAsync", ex);
                throw ex;
            }
        }, executor);
    }

    @Override
    public void update(T model) {
        try {
            logger.debug("Update %s", model);
            eql.update(t__, model).execute();
        } catch (Exception e) {
            logger.error("Error on update", e);
            throw e;
        }
    }


    public CompletableFuture<Void> deleteAsync(int id) {
        return CompletableFuture.runAsync(() -> {
            try {
                delete(id);
            } catch (Exception ex) {
                logger.error("Error on deleteAsync", ex);
                throw ex;
            }
        }, executor);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void delete(int id) {
        try {
            var properties = t__.allPropertyMetamodels();

            for (PropertyMetamodel<?> prop : properties) {
                if (prop.asType().isId() && prop.asClass().equals(Integer.class)) {
                    PropertyMetamodel<Integer> isId;

                    isId = (PropertyMetamodel<Integer>) prop;

                    Consumer<WhereDeclaration> cc = whereDeclaration -> {
                        whereDeclaration.eq(isId, id);
                    };

                    // var txx = tx.getTransaction();
                    // txx.begin();
                    // /*var result =*/ nql.delete(t__).where(cc).execute();
                    // txx.commit();

                    //tx.required(() -> {
                        nql.delete(t__).where(cc).execute();
                    //});

                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void delete(String id) {
        try {
            var properties = t__.allPropertyMetamodels();

            for (PropertyMetamodel<?> prop : properties) {
                if (prop.asType().isId() && prop.asClass().equals(String.class)) {
                    PropertyMetamodel<String> isId;

                    isId = (PropertyMetamodel<String>) prop;

                    Consumer<WhereDeclaration> cc = whereDeclaration -> {
                        whereDeclaration.eq(isId, id);
                    };

                    var txx = tx.getTransaction();
                    txx.begin();
                    /* var result = */ nql.delete(t__).where(cc).execute();
                    txx.commit();
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getById(int id) {
        // inspect if exists Pk in metamodel
        // todo: Integer
        try {
            var properties = t__.allPropertyMetamodels();

            for (PropertyMetamodel<?> prop : properties) {
                if (prop.asType().isId() && prop.asClass().equals(Integer.class)) {
                    PropertyMetamodel<Integer> isId;

                    isId = (PropertyMetamodel<Integer>) prop;

                    Consumer<WhereDeclaration> cc = whereDeclaration -> {
                        whereDeclaration.eq(isId, id);
                    };

                    // var txx = tx.getTransaction();
                    // txx.begin();
                    var result = eql.from(t__)
                            .where(cc)
                            .fetchOne();
                    // txx.commit();
                    return result;
                }
            }

            throw new UnsupportedOperationException("Unimplemented method 'getById'");
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getById(String id) {
        // inspect if exists Pk in metamodel
        // todo: Integer
        try {
            var properties = t__.allPropertyMetamodels();

            for (PropertyMetamodel<?> prop : properties) {
                if (prop.asType().isId() && prop.asClass().equals(String.class)) {
                    PropertyMetamodel<String> isId;

                    isId = (PropertyMetamodel<String>) prop;

                    Consumer<WhereDeclaration> cc = whereDeclaration -> {
                        whereDeclaration.eq(isId, id);
                    };

                    var txx = tx.getTransaction();
                    txx.begin();
                    var result = eql.from(t__)
                            .where(cc)
                            .fetchOne();
                    txx.commit();
                    return result;
                }
            }

            throw new UnsupportedOperationException("Unimplemented method 'getById'");
        } catch (Exception ex) {
            throw ex;
        }
    }

}
