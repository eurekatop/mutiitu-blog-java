package com.mutiitu.dao;

import java.util.function.Function;

import org.jooq.impl.DSL;
import org.seasar.doma.jdbc.criteria.metamodel.EntityMetamodel;
import org.seasar.doma.jdbc.criteria.query.SelectBuilder;
import org.seasar.doma.jdbc.criteria.tuple.Row;
import org.seasar.doma.jdbc.entity.EntityType;
import org.seasar.doma.jdbc.entity.Property;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.mutiitu.core.FieldMetadata;
import com.mutiitu.core.FieldMetadataReader;
import com.mutiitu.core.FieldSelector;
import com.mutiitu.core.ModelCrudDaoImpl;
import com.mutiitu.domain.AuthorModel_;
import com.mutiitu.domain.BlogEntryModel;
import com.mutiitu.domain.BlogEntryModel_;
import com.mutiitu.persistence.SQLiteDB;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//

import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;

import java.io.Serial;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;


public class BlogEntryDao extends ModelCrudDaoImpl<BlogEntryModel, BlogEntryModel_> {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    String tableName = BlogEntryModel.class.getAnnotation(org.seasar.doma.Table.class).name();
    Table<?> table = DSL.table(tableName);
    List<FieldMetadata> metaDataFields = FieldMetadataReader.getFieldMetadata(BlogEntryModel.class);


    @Inject
    public BlogEntryDao(SQLiteDB SQLiteDB) {
        super(new BlogEntryModel_(), new BlogEntryModel(), SQLiteDB);
    }

    public BlogEntryModel getById(int id) {
        return 
                queryDsl
                .from(entityMetaModel).where(c -> c.eq(entityMetaModel.id, id))
                .fetchOne();
    }

    public BlogEntryModel getWithAuthorsById(int id) {
        var a = new AuthorModel_();

        return queryDsl
                .from(entityMetaModel)
                .leftJoin(a, on -> on.eq(entityMetaModel.authorId, a.id))
                .where(c -> c.eq(entityMetaModel.id, id))
                .associate(entityMetaModel, a, (blogEntry, author) -> {
                    blogEntry.setAuthor(author);
                    // author.getBlogEntries().add(blogEntry);
                })
                .fetchOne();
    }

    public List<Integer> getIds() {
        return queryDsl
                .from(entityMetaModel)
                .select(entityMetaModel.id)
                .fetch();
    }

    public List<BlogEntryModel> getBlogs(int count) {
         return queryDsl
                .from(entityMetaModel)
                .where(c -> c.lt(entityMetaModel.id, 4000))
                .fetch();
    }

    public List<Object> getBlogsPartial(int count) {
        //return queryDsl
        //    .from(entityModel)
        //    .where( c -> c.lt(entityModel.id, 4000))
        //    .orderBy( c -> c.asc(entityModel.id))
        //    .limit(count)
        //    .selectAsRow(entityModel.id, entityModel.title, entityModel.resume, entityModel.subtitle)
        //    .stream()
        //    .map( row -> {
        //        var dto = new BlogEntryPartialDto();
        //        dto.id = row.get(entityModel.id);
        //        dto.title = row.get(entityModel.title);
        //        dto.resume = row.get(entityModel.resume);
        //        dto.subtitle = row.get(entityModel.subtitle);
        //        return dto;
        //    }).toList();

        return null;
    }

    // to jooq
    public List<BlogEntryModel> getBlogsJooq(int count) {

        // get annotation table name
        var tableName = BlogEntryModel.class.getAnnotation(org.seasar.doma.Table.class).name();
                
        var model = new BlogEntryModel_();
        var fields = new ArrayList<Field<?>>();



        var data = dslContext
                .selectFrom(tableName)
                .fetch()
                .into(BlogEntryModel.class);
            
        return data;
    }

    public List<Object> getBlogsJooqPartial(int count) {
        // Get the table name from the annotation
        String tableName = BlogEntryModel.class.getAnnotation(org.seasar.doma.Table.class).name();

        // Create a list to hold JOOQ Names
        List<Name> columns = new ArrayList<>();
        columns.add(DSL.name("ID"));
        columns.add(DSL.name("TITLE"));
        columns.add(DSL.name("RESUME"));
        columns.add(DSL.name("SUBTITLE"));

        // Create the field array based on the Names
        Field<?>[] fields = new Field<?>[columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            fields[i] = DSL.field(columns.get(i));
        }

        // Create table object dynamically from the table name
        Table<?> table = DSL.table(tableName); 

        //var data = dslContext
        //.select(fields)
        //.from(tableName)
        //.limit(count)
        //.fetch(record -> {
        //    BlogEntryPartialDto dto = new BlogEntryPartialDto();
        //    dto.id = record.get("ID", Integer.class);
        //    dto.title = record.get("TITLE", String.class);
        //    dto.resume = record.get("RESUME", String.class);
        //    dto.subtitle = record.get("SUBTITLE", String.class);
        //    return dto;
        //});

        return null;
    }

    
    @Deprecated
    public List<Map<String, Object>> getBlogsJooqPartialDynamic(int count, List<String> fieldsToSelect)  {

        // from the list fields to select create a fields with column name and type
        List<Field<?>> fields = fieldsToSelect
            .stream()
            .map(fieldName -> {
                var field = metaDataFields.stream().filter(f -> f.getFieldName().equals(fieldName)).findFirst().get();
                return DSL.field(DSL.name(field.getColumnName()), field.getFieldType());
            })
            .collect(Collectors.toList());


        var data = dslContext
            .select(fields)
            .from(tableName)
            .limit(count)
            .fetch(record -> {
                Map<String, Object> propsAndValues = new HashMap<>();
                for (int i = 0; i < fieldsToSelect.size(); i++) {
                    String fieldName = fieldsToSelect.get(i);
                    FieldMetadata fieldMeta = metaDataFields.stream()
                        .filter(f -> f.getFieldName().equals(fieldName))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Field not found: " + fieldName));
                    // Add the field name and its corresponding value from the record
                    propsAndValues.put(fieldName, record.get(fieldMeta.getColumnName()));
                }
                return propsAndValues;
            });
            //.into(BlogEntryPartialDto.class);

        return data;
    }
    

    @Deprecated
    public <T> List<T> getBlogsJooqPartialDynamicDto(int count, List<String> fieldsToSelect, Class<T> destinationClass)  {

        // from the list fields to select create a fields with column name and type
        List<Field<?>> fields = fieldsToSelect
            .stream()
            .map(fieldName -> {
                var field = metaDataFields.stream().filter(f -> f.getFieldName().equals(fieldName)).findFirst().get();
                return DSL.field(DSL.name(field.getColumnName()), field.getFieldType());
            })
            .collect(Collectors.toList());


        var data = dslContext
            .select(fields)
            .from(tableName)
            .limit(count)
            .fetch()
            .into(destinationClass);

        return data;
    }



}
