package com.mutiitu.domain;

import com.google.inject.Singleton;

public interface  ModelCrud<T> {
    void insert(T model);

    T getById(int id);
    T getById(String id);

    void delete(int id);
    void delete(String id);
}
