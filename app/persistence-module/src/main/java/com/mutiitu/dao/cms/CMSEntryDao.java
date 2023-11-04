package com.mutiitu.dao.cms;

import com.google.inject.Inject;
import com.mutiitu.dao.ModelCrudDaoImpl;
import com.mutiitu.domain.cms.CmsEntryModel;
import com.mutiitu.domain.cms.CmsEntryModel_;
import com.mutiitu.persistence.SQLiteDB;

public class CMSEntryDao extends ModelCrudDaoImpl<CmsEntryModel, CmsEntryModel_> {

    @Inject
    public CMSEntryDao(SQLiteDB SQLiteDB) {
        super(new CmsEntryModel_(), SQLiteDB);
    }

}
