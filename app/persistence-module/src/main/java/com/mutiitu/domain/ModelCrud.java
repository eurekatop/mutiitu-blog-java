package com.mutiitu.domain;

import java.util.concurrent.CompletableFuture;

public interface  ModelCrud<T> {
    void insert(T model);
    CompletableFuture<Void> insertAsync(T model);


    T getById(int id);
    T getById(String id);

    void delete(int id);
    CompletableFuture<Void> deleteAsync(int id);

    void delete(String id);
}
