package com.mutiitu.dao;

import java.util.List;


import com.google.inject.Inject;
import com.mutiitu.core.ModelCrudDaoImpl;
import com.mutiitu.domain.CmsEntryModel;
import com.mutiitu.domain.CmsEntryModel_;
import com.mutiitu.persistence.DatabaseConfig;

public class CMSEntryDao extends ModelCrudDaoImpl<CmsEntryModel, CmsEntryModel_> {

    @Inject
    public CMSEntryDao(DatabaseConfig SQLiteDB) {
        super(new CmsEntryModel_(), new CmsEntryModel(), SQLiteDB);
    }


    public List<CmsEntryModel> getCmsEntries(int count) {
        return queryDsl
                .from(entityMetaModel)
                .where(c -> c.lt(entityMetaModel.id, count))
                .fetch();
    }

    public CmsEntryModel getBySlug(String slug) {
        return queryDsl
                .from(entityMetaModel)
                .where(c -> c.eq(entityMetaModel.slug, slug))
                .fetchOne();
    }



}
