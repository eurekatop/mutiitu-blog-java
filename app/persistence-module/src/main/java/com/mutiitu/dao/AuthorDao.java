package com.mutiitu.dao;

import com.mutiitu.domain.AuthorModel;
import com.mutiitu.domain.AuthorModel_;

public class AuthorDao extends ModelCrudDaoImpl<AuthorModel, AuthorModel_> {
    public AuthorDao() {
        super(new AuthorModel_());
    }
}
