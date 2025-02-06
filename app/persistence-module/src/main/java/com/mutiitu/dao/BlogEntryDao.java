package com.mutiitu.dao;

import java.util.List;

import org.seasar.doma.jdbc.criteria.metamodel.EntityMetamodel;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.mutiitu.domain.AuthorModel_;
import com.mutiitu.domain.BlogEntryModel;
import com.mutiitu.domain.BlogEntryModel_;
import com.mutiitu.persistence.SQLiteDB;

public class BlogEntryDao extends ModelCrudDaoImpl<BlogEntryModel, BlogEntryModel_> {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

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
}
