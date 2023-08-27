package com.mutiitu.domain;

interface  ModelCrud<T> {
    void insert(T model);

    T getById(int id);
    T getById(String id);

    void delete(int id);
    void delete(String id);


}
