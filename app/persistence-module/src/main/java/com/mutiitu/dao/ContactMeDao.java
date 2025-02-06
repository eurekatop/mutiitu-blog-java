package com.mutiitu.dao;

import java.util.List;
import com.google.inject.Inject;
import com.mutiitu.domain.ContactMeModel;
import com.mutiitu.domain.ContactMeModel_;
import com.mutiitu.persistence.SQLiteDB;

public class ContactMeDao extends ModelCrudDaoImpl<ContactMeModel, ContactMeModel_> {
    // private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    public ContactMeDao(SQLiteDB SQLiteDB) {
        super(new ContactMeModel_(), SQLiteDB);
    }

    public ContactMeModel getById(int id) {
        return queryDsl
                .from(entityModel)
                    .where(c -> c.eq(entityModel.id, id))
                .fetchOne();
    }

    public List<Integer> getIds() {
        return queryDsl
                .from(entityModel)
                .select(entityModel.id)
                .fetch();
    }

    public List<ContactMeModel> getContactMes() {
        return queryDsl
                .from(entityModel)
                .where(c -> c.lt(entityModel.id, 200))
                .fetch();
    }

}
