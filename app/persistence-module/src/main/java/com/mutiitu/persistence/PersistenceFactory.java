package com.mutiitu.persistence;

import java.util.HashMap;
import java.util.Map;

import com.mutiitu.domain.FooterModel;
import com.mutiitu.domain.FooterModel_;
import com.mutiitu.domain.HeaderModel;
import com.mutiitu.domain.HeaderModel_;
import com.mutiitu.domain.ModelCrud;
import com.mutiitu.domain.ModelCrudImpl;
import com.mutiitu.domain.TranslateModel;
import com.mutiitu.domain.TranslateModel_;

public class PersistenceFactory<T> {

    private final Map<Class<?>, ModelCrud<?>> modelCrudMap = new HashMap<>();



    public PersistenceFactory(){
        // TODO: generate ??
        modelCrudMap.put(HeaderModel.class, new ModelCrudImpl<>(new HeaderModel_()));
        modelCrudMap.put(FooterModel.class, new ModelCrudImpl<>(new FooterModel_()));
        modelCrudMap.put(TranslateModel.class, new ModelCrudImpl<>(new TranslateModel_()));
    }


    @SuppressWarnings("unchecked")
    public ModelCrud<T> create(Class<?> clazz){
        ModelCrud<?> modelCrud = modelCrudMap.get(clazz);
        if (modelCrud != null) {
            return (ModelCrud<T>) modelCrud;
        } else {
            throw new IllegalArgumentException("No valid implementation was found for the provided class.");
        }
    }
}
