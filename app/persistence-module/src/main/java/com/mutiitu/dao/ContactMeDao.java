package com.mutiitu.dao;

import java.util.List;
import com.google.inject.Inject;
import com.mutiitu.core.ModelCrudDaoImpl;
import com.mutiitu.domain.ContactMeModel;
import com.mutiitu.domain.ContactMeModel_;
import com.mutiitu.persistence.DatabaseConfig;

public class ContactMeDao extends ModelCrudDaoImpl<ContactMeModel, ContactMeModel_> {
    // private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    public ContactMeDao(DatabaseConfig SQLiteDB) {
        super(new ContactMeModel_(), SQLiteDB);
    }

    public ContactMeModel getById(int id) {
        return queryDsl
                .from(entityMetaModel)
                    .where(c -> c.eq(entityMetaModel.id, id))
                .fetchOne();
    }

    public List<Integer> getIds() {
        return queryDsl
                .from(entityMetaModel)
                .select(entityMetaModel.id)
                .fetch();
    }

    public List<ContactMeModel> getContactMes() {
        return queryDsl
                .from(entityMetaModel)
                .where(c -> c.lt(entityMetaModel.id, 200))
                .fetch();
    }

}
