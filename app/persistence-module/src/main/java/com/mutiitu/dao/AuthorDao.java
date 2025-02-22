package com.mutiitu.dao;

import com.google.inject.Inject;
import com.mutiitu.core.ModelCrudDaoImpl;
import com.mutiitu.domain.AuthorModel;
import com.mutiitu.domain.AuthorModel_;
import com.mutiitu.persistence.DatabaseConfig;

public class AuthorDao extends ModelCrudDaoImpl<AuthorModel, AuthorModel_> {
    @Inject
    public AuthorDao(DatabaseConfig SQLiteDB) {
        super(new AuthorModel_(), SQLiteDB);
    }
}
