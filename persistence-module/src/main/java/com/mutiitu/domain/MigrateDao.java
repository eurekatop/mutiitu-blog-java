package com.mutiitu.domain;

import org.seasar.doma.Dao;
import org.seasar.doma.Script;
import org.seasar.doma.Sql;

/*
 * H2DB
 *     
   CREATE TABLE IF NOT EXISTS Header ( 
      id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, 
      title VARCHAR(50) NOT NULL
   );
   
    CREATE TABLE IF NOT EXISTS Footer ( 
      customId INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, 
      title VARCHAR(50) NOT NULL
    );

    CREATE TABLE IF NOT EXISTS Translate ( 
      language VARCHAR(50) NOT NULL,
      "value" VARCHAR(50) NOT NULL
   );

 */

@Dao
public interface MigrateDao {
  @Sql("""
    CREATE TABLE IF NOT EXISTS Header2 (
      id INT AUTO_INCREMENT PRIMARY KEY,
      TITLE VARCHAR(50) NOT NULL,
      SUBTITLE VARCHAR(50) NOT NULL
    );
   """)
  @Script
  void create();
}
