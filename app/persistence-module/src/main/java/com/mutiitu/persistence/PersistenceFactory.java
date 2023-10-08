package com.mutiitu.persistence;

import java.util.HashMap;
import java.util.Map;

import com.mutiitu.dao.ModelCrudDao;
import com.mutiitu.dao.ModelCrudDaoImpl;
import com.mutiitu.domain.AuthorModel;
import com.mutiitu.domain.AuthorModel_;
import com.mutiitu.domain.BlogEntryModel;
import com.mutiitu.domain.BlogEntryModel_;
import com.mutiitu.domain.FooterModel;
import com.mutiitu.domain.FooterModel_;
import com.mutiitu.domain.HeaderModel;
import com.mutiitu.domain.HeaderModel_;
import com.mutiitu.domain.TranslateModel;
import com.mutiitu.domain.TranslateModel_;

@Deprecated
public class PersistenceFactory<T> {

    private final Map<Class<?>, ModelCrudDao<?>> modelCrudMap = new HashMap<>();

    public PersistenceFactory() {
        // TODO: generate ??
        modelCrudMap.put(HeaderModel.class, new ModelCrudDaoImpl<>(new HeaderModel_()));
        modelCrudMap.put(FooterModel.class, new ModelCrudDaoImpl<>(new FooterModel_()));
        modelCrudMap.put(TranslateModel.class, new ModelCrudDaoImpl<>(new TranslateModel_()));
        modelCrudMap.put(AuthorModel.class, new ModelCrudDaoImpl<>(new AuthorModel_()));
        modelCrudMap.put(BlogEntryModel.class, new ModelCrudDaoImpl<>(new BlogEntryModel_()));
    }

    @SuppressWarnings("unchecked")
    public ModelCrudDao<T> create(Class<?> clazz) {
        ModelCrudDao<?> modelCrud = modelCrudMap.get(clazz);
        if (modelCrud != null) {
            return (ModelCrudDao<T>) modelCrud;
        } else {
            throw new IllegalArgumentException("No valid implementation was found for the provided class.");
        }
    }
}
