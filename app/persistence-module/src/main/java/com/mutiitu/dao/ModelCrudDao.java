package com.mutiitu.dao;

import java.util.concurrent.CompletableFuture;

public interface ModelCrudDao<T> {
    void insert(T model);

    CompletableFuture<Void> insertAsync(T model);

    void update(T model);

    CompletableFuture<Void> updateAsync(T model);

    T getById(int id);

    T getById(String id);

    void delete(int id);

    CompletableFuture<Void> deleteAsync(int id);

    void delete(String id);

}
