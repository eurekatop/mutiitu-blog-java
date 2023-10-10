package com.mutiitu.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.builder.SelectBuilder;

@Dao
public interface EmpoyeeDao {

    default int count() {
        Config config = Config.get(this);
        SelectBuilder builder = SelectBuilder.newInstance(config);
        builder.sql("select count(*) from employee");
        return builder.getScalarSingleResult(int.class);
    }
}