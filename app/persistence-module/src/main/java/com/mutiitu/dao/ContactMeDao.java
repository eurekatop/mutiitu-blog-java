package com.mutiitu.dao;

import java.util.List;

import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.mutiitu.domain.ContactMeModel;
import com.mutiitu.domain.ContactMeModel_;
import com.mutiitu.persistence.SQLiteDB;

public class ContactMeDao extends ModelCrudDaoImpl<ContactMeModel, ContactMeModel_> {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    public ContactMeDao(SQLiteDB SQLiteDB) {
        super(new ContactMeModel_(), SQLiteDB);
    }

    public ContactMeModel getById(int id) {
        return eql
                .from(t__).where(c -> c.eq(t__.id, id))
                .fetchOne();
    }

    public List<Integer> getIds() {
        return nql
                .from(t__)
                .select(t__.id)
                .fetch();
    }

    public List<ContactMeModel> getContactMes() {
        return eql
                .from(t__)
                .where(c -> c.lt(t__.id, 200))
                .fetch();
    }

}
