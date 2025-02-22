package com.mutiitu.dao.cms;

import com.google.inject.Inject;
import com.mutiitu.core.ModelCrudDaoImpl;
import com.mutiitu.domain.cms.CmsEntryModel;
import com.mutiitu.domain.cms.CmsEntryModel_;
import com.mutiitu.persistence.DatabaseConfig;

public class CMSEntryDao extends ModelCrudDaoImpl<CmsEntryModel, CmsEntryModel_> {

    @Inject
    public CMSEntryDao(DatabaseConfig SQLiteDB) {
        super(new CmsEntryModel_(), SQLiteDB);
    }

}
