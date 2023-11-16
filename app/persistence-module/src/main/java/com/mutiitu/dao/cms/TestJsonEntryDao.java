package com.mutiitu.dao.cms;

import com.google.inject.Inject;
import com.mutiitu.dao.ModelCrudDaoImpl;
import com.mutiitu.domain.cms.TestJsonEntryModel;
import com.mutiitu.domain.cms.TestJsonEntryModel_;
import com.mutiitu.persistence.SQLiteDB;

public class TestJsonEntryDao extends ModelCrudDaoImpl<TestJsonEntryModel, TestJsonEntryModel_> {

    @Inject
    public TestJsonEntryDao(SQLiteDB SQLiteDB) {
        super(new TestJsonEntryModel_(), SQLiteDB);
    }

}
