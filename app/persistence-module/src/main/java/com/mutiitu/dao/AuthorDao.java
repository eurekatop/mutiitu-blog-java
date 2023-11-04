package com.mutiitu.dao;

import com.google.inject.Inject;
import com.mutiitu.domain.AuthorModel;
import com.mutiitu.domain.AuthorModel_;
import com.mutiitu.persistence.SQLiteDB;

public class AuthorDao extends ModelCrudDaoImpl<AuthorModel, AuthorModel_> {
    @Inject
    public AuthorDao(SQLiteDB SQLiteDB) {
        super(new AuthorModel_(), SQLiteDB);
    }
}
