package com.mutiitu.domain;

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

import com.mutiitu.persistence.BaseModel;
import com.mutiitu.persistence.SQLiteDB;

public class ModelCrudImpl<T extends BaseModel, T1 extends EntityMetamodel<T>> implements ModelCrud<T> {
    private final int THREAD_POOL = 40;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL); 

    Entityql eql;
    NativeSql nql;
    LocalTransactionManager tx;
    T1 t__ ;


    public ModelCrudImpl(T1 doma_ ){
        tx = SQLiteDB.singleton().getTransactionManager();
        eql = new Entityql(SQLiteDB.singleton());
        nql = new NativeSql(SQLiteDB.singleton());
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

    @Override
    public void insert(T model) {
        try {
            // var txx = tx.getTransaction();
            // txx.begin();
            // eql.insert( t__ , model).execute();
            // txx.commit();

            tx.required( 
                () -> {
                    eql.insert( t__ , model).execute();
                }
            );

        } catch (Exception e) {
            logger.error("Error on insert", e);
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
                if ( prop.asType().isId() && prop.asClass().equals( Integer.class) ) {
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
                        nql.delete(t__).where(cc).execute();
                    });

                    
                }
            }
        }
        catch (Exception ex){
            throw ex;
        }    
    }

    @Override
    @SuppressWarnings("unchecked")
    public void delete(String id) {
        try {
            var properties = t__.allPropertyMetamodels();

            for (PropertyMetamodel<?> prop : properties) {
                if ( prop.asType().isId() && prop.asClass().equals( String.class) ) {
                    PropertyMetamodel<String> isId;

                    isId = (PropertyMetamodel<String>) prop;

                    Consumer<WhereDeclaration> cc = whereDeclaration -> {
                        whereDeclaration.eq(isId, id);
                    };

                    var txx = tx.getTransaction();
                    txx.begin();
                    /*var result =*/ nql.delete(t__).where(cc).execute();
                    txx.commit();
                }
            }
        }
        catch (Exception ex){
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
                if ( prop.asType().isId() && prop.asClass().equals( Integer.class) ) {
                    PropertyMetamodel<Integer> isId;

                    isId = (PropertyMetamodel<Integer>) prop;

                    Consumer<WhereDeclaration> cc = whereDeclaration -> {
                        whereDeclaration.eq(isId, id);
                    };

                    var txx = tx.getTransaction();
                    txx.begin();
                    var result = eql.from(t__)
                    .where( cc )
                    .fetchOne();
                    txx.commit();
                    return result;
                }
            }

            throw new UnsupportedOperationException("Unimplemented method 'getById'");
        }
        catch (Exception ex){
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
                if ( prop.asType().isId() && prop.asClass().equals( String.class) ) {
                    PropertyMetamodel<String> isId;

                    isId = (PropertyMetamodel<String>) prop;

                    Consumer<WhereDeclaration> cc = whereDeclaration -> {
                        whereDeclaration.eq(isId, id);
                    };

                    var txx = tx.getTransaction();
                    txx.begin();
                    var result = eql.from(t__)
                    .where( cc )
                    .fetchOne();
                    txx.commit();
                    return result;
                }
            }

            throw new UnsupportedOperationException("Unimplemented method 'getById'");
        }
        catch (Exception ex){
            throw ex;
        }
    }


}
