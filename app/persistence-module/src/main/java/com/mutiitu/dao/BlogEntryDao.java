package com.mutiitu.dao;

import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.mutiitu.domain.BlogEntryModel;
import com.mutiitu.domain.BlogEntryModel_;
import com.mutiitu.persistence.SQLiteDB;

public class BlogEntryDao extends ModelCrudDaoImpl<BlogEntryModel, BlogEntryModel_> {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    public BlogEntryDao(SQLiteDB SQLiteDB) {
        super(new BlogEntryModel_(), SQLiteDB);
    }

    public BlogEntryModel getWithAuthorsById(int id) {
        return eql
                .from(t__).where(c -> c.eq(t__.id, id))
                .fetchOne();

    }

}
