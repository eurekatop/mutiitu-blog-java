package com.mutiitu.persistence;

import com.mutiitu.domain.FooterModel;
import com.mutiitu.domain.FooterModel_;
import com.mutiitu.domain.HeaderModel;
import com.mutiitu.domain.HeaderModel_;
import com.mutiitu.domain.ModelCrud;
import com.mutiitu.domain.ModelCrudImpl;
import com.mutiitu.domain.TranslateModel;
import com.mutiitu.domain.TranslateModel_;

public class PersistenceFactory<T> {

    public PersistenceFactory(){}


    @SuppressWarnings("unchecked")
    public ModelCrud<T> create(Class<?> clazz){
        if ( clazz.equals(HeaderModel.class) ){
            var instance = new ModelCrudImpl<HeaderModel, HeaderModel_>( new HeaderModel_() );
            return (ModelCrud<T>) (instance);
        }
 
        if ( clazz.equals(FooterModel.class) ){
            var instance = new ModelCrudImpl<FooterModel, FooterModel_>( new FooterModel_() );
            return (ModelCrud<T>) (instance);
        }

        if ( clazz.equals(TranslateModel.class) ){
            var instance = new ModelCrudImpl<TranslateModel, TranslateModel_>( new TranslateModel_() );
            return (ModelCrud<T>) (instance);
        }


 
        throw new Error();
    }
}
