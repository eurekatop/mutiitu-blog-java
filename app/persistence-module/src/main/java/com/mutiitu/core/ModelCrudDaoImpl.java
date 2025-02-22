package com.mutiitu.core;

import com.mutiitu.domain.BlogEntryModel;
import com.mutiitu.persistence.BaseModel;
import com.mutiitu.persistence.SQLiteDB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.seasar.doma.jdbc.criteria.declaration.WhereDeclaration;
import org.seasar.doma.jdbc.criteria.metamodel.EntityMetamodel;
import org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.slf4j.LoggerFactory;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.seasar.doma.jdbc.criteria.QueryDsl;


public class ModelCrudDaoImpl<T extends BaseModel, T1 extends EntityMetamodel<T>> implements ModelCrudDao<T> {
    private final int THREAD_POOL = 5; // TODO: environment variable;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL);

    SQLiteDB SQLiteDB;

    protected QueryDsl queryDsl;
    protected DSLContext dslContext;

    protected LocalTransactionManager tx;
    protected T1 entityMetaModel;
    protected T entityModel;

    public ModelCrudDaoImpl(T1 entityMetaModel, SQLiteDB SQLiteDB) {
        tx = SQLiteDB.getTransactionManager();
        this.entityMetaModel = entityMetaModel;
        this.SQLiteDB = SQLiteDB;
        this.queryDsl = new QueryDsl(SQLiteDB);
        this.dslContext = SQLiteDB.getDslContext();
    }

    public ModelCrudDaoImpl(T1 entityMetaModel, T entityModel, SQLiteDB SQLiteDB) {
        tx = SQLiteDB.getTransactionManager();
        this.entityMetaModel = entityMetaModel;
        this.entityModel = entityModel;
        this.SQLiteDB = SQLiteDB;
        this.queryDsl = new QueryDsl(SQLiteDB);
        this.dslContext = SQLiteDB.getDslContext();
    }


    public ModelCrudDaoImpl(T1 entityMetaModel) {
        tx = SQLiteDB.getTransactionManager();
        queryDsl = new QueryDsl(SQLiteDB);
        this.entityMetaModel = entityMetaModel;
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
            tx.required(
                    () -> {
                        queryDsl.insert(entityMetaModel).single(model).execute();
                    });
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
            queryDsl.update(entityMetaModel);
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
            var properties = entityMetaModel.allPropertyMetamodels();

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

                    tx.required(() -> {
                        queryDsl.delete(entityMetaModel).where(cc).execute();
                    });
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    //@SuppressWarnings("unchecked")
    public void delete(String id) {
        try {
            var properties = entityMetaModel.allPropertyMetamodels();

            for (PropertyMetamodel<?> prop : properties) {
                if (prop.asType().isId() && prop.asClass().equals(String.class)) {
                    PropertyMetamodel<String> isId;

                    isId = (PropertyMetamodel<String>) prop;

                    Consumer<WhereDeclaration> cc = whereDeclaration -> {
                        whereDeclaration.eq(isId, id);
                    };

                    var txx = tx.getTransaction();
                    txx.begin();
                    /* var result = */ queryDsl.delete(entityMetaModel).where(cc).execute();
                    txx.commit();
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    //@SuppressWarnings("unchecked")
    public T getById(int id) {
        // inspect if exists Pk in metamodel
        // todo: Integer
        try {

            var properties = entityMetaModel.allPropertyMetamodels();

            for (PropertyMetamodel<?> prop : properties) {
                if (prop.asType().isId() && prop.asClass().equals(Integer.class)) {
                    PropertyMetamodel<Integer> isId;

                    isId = (PropertyMetamodel<Integer>) prop;

                    Consumer<WhereDeclaration> cc = whereDeclaration -> {
                        whereDeclaration.eq(isId, id);
                    };

                    var txx = tx.getTransaction();
                    txx.begin();
                    var result = queryDsl.from(entityMetaModel)
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

    @Override
    @SuppressWarnings("unchecked")
    public T getById(String id) {
        // inspect if exists Pk in metamodel
        // todo: Integer
        try {
            var properties = entityMetaModel.allPropertyMetamodels();

            for (PropertyMetamodel<?> prop : properties) {
                if (prop.asType().isId() && prop.asClass().equals(String.class)) {
                    PropertyMetamodel<String> isId;

                    isId = (PropertyMetamodel<String>) prop;

                    Consumer<WhereDeclaration> cc = whereDeclaration -> {
                        whereDeclaration.eq(isId, id);
                    };

                    var txx = tx.getTransaction();
                    txx.begin();
                    var result = queryDsl.from(entityMetaModel)
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


    public <P> List<P> getPartialDynamic(int count, List<String> fieldsToSelect, Class<P> destinationClass) {

        String tableName = entityModel.getClass().getAnnotation(org.seasar.doma.Table.class).name();
        List<FieldMetadata> metaDataFields = FieldMetadataReader.getFieldMetadata(entityModel.getClass());

        List<Field<?>> fields = fieldsToSelect
            .stream()
            .map(fieldName -> {
                var field = metaDataFields.stream().filter(f -> f.getFieldName().equals(fieldName)).findFirst().get();
                return DSL.field(DSL.name(field.getColumnName()), field.getFieldType());
            })
            .collect(Collectors.toList());


        var data = dslContext
            .select(fields)
            .from(tableName)
            .limit(count)
            .fetch()
            .into(destinationClass);

        return data;
    }

    

}
