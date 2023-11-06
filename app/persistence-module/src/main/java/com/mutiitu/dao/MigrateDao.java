package com.mutiitu.dao;

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

      CREATE TABLE IF NOT EXISTS Footer (
        customId INT AUTO_INCREMENT PRIMARY KEY,
        TITLE VARCHAR(50) NOT NULL
      );

      /*
      CREATE TABLE IF NOT EXISTS Author (
        id INT AUTO_INCREMENT PRIMARY KEY,
        NAME VARCHAR(50) NOT NULL,
        SURNAME VARCHAR(50) NOT NULL,
        PASSWORD VARCHAR(2000) NOT NULL
      );
      */
      ALTER TABLE test_mu.Author ADD PASSWORD varchar(2000) NULL;


      CREATE TABLE IF NOT EXISTS BlogEntry (
        id INT AUTO_INCREMENT PRIMARY KEY,
        TITLE VARCHAR(50) NOT NULL,
        SUBTITLE VARCHAR(50) NOT NULL,
        CONTENT LONGTEXT NOT NULL,
        AUTHOR_ID INT,
        FOREIGN KEY (AUTHOR_ID) REFERENCES Author(id)
      );

      CREATE TABLE IF NOT EXISTS ContactMe (
        id INT AUTO_INCREMENT PRIMARY KEY,
        NAME VARCHAR(50) NOT NULL,
        MESSAGE LONGTEXT NOT NULL,
        SUBJECT LONGTEXT NOT NULL,
        EMAIL VARCHAR(255) NOT NULL
      );

      CREATE TABLE IF NOT EXISTS CmsEntry (
        id INT AUTO_INCREMENT PRIMARY KEY,
        TITLE VARCHAR(50) NOT NULL,
        CONTENT LONGTEXT NOT NULL,
        STATUS VARCHAR(50) NOT NULL,
        AUTHOR_ID INT,
        DATE VARCHAR(50) NOT NULL,
        EXCERPT VARCHAR(50) NOT NULL,
        THUMBNAIL VARCHAR(50) NOT NULL,
        SLUG VARCHAR(50) NOT NULL
      );

      """)
  @Script
  void create();
}
