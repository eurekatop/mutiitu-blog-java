package com.mutiitu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.doma.jdbc.criteria.metamodel.EntityMetamodel;
import org.seasar.doma.jdbc.criteria.query.SelectBuilder;
import org.seasar.doma.jdbc.criteria.tuple.Row;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.mutiitu.domain.AuthorModel_;
import com.mutiitu.domain.BlogEntryModel;
import com.mutiitu.domain.BlogEntryModel_;
import com.mutiitu.persistence.SQLiteDB;



public class BlogEntryDao extends ModelCrudDaoImpl<BlogEntryModel, BlogEntryModel_> {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    public class BlogEntryPartialDto {
        public int id;
        public String title;
        public String resume;
        public String subtitle;
    }
    
    @Inject
    public BlogEntryDao(SQLiteDB SQLiteDB) {
        super(new BlogEntryModel_(), SQLiteDB);
    }

    public BlogEntryModel getById(int id) {
        return 
                queryDsl
                .from(entityModel).where(c -> c.eq(entityModel.id, id))
                .fetchOne();
    }

    public BlogEntryModel getWithAuthorsById(int id) {
        var a = new AuthorModel_();

        return queryDsl
                .from(entityModel)
                .leftJoin(a, on -> on.eq(entityModel.authorId, a.id))
                .where(c -> c.eq(entityModel.id, id))
                .associate(entityModel, a, (blogEntry, author) -> {
                    blogEntry.setAuthor(author);
                    // author.getBlogEntries().add(blogEntry);
                })
                .fetchOne();
    }

    public List<Integer> getIds() {
        return queryDsl
                .from(entityModel)
                .select(entityModel.id)
                .fetch();
    }

    public List<BlogEntryModel> getBlogs(int count) {
         return queryDsl
                .from(entityModel)
                .where(c -> c.lt(entityModel.id, 4000))
                .fetch();
    }

    public List<BlogEntryPartialDto> getBlogsPartial(int count) {
        return queryDsl
            .from(entityModel)
            .where( c -> c.lt(entityModel.id, 4000))
            .orderBy( c -> c.asc(entityModel.id))
            .limit(count)
            .selectAsRow(entityModel.id, entityModel.title, entityModel.resume, entityModel.subtitle)
            .stream()
            .map( row -> {
                var dto = new BlogEntryPartialDto();
                dto.id = row.get(entityModel.id);
                dto.title = row.get(entityModel.title);
                dto.resume = row.get(entityModel.resume);
                dto.subtitle = row.get(entityModel.subtitle);
                return dto;
            }).toList();
    }

    // to jooq
    public List<BlogEntryModel> getBlogsJooq(int count) {

        // get annotation table name
        var tableName = BlogEntryModel.class.getAnnotation(org.seasar.doma.Table.class).name();
                

        var data = dslContext.selectFrom(tableName)
                .fetch()
                .into(BlogEntryModel.class);
            
        return data;
    }


}
