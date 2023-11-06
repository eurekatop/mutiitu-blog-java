package com.mutiitu.dao;

import java.util.List;

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
        return eql
                .from(t__).where(c -> c.eq(t__.id, id))
                .fetchOne();
    }

    public BlogEntryModel getWithAuthorsById(int id) {
        var a = new AuthorModel_();

        return eql
                .from(t__)
                .leftJoin(a, on -> on.eq(t__.authorId, a.id))
                .where(c -> c.eq(t__.id, id))
                .associate(t__, a, (blogEntry, author) -> {
                    blogEntry.setAuthor(author);
                    // author.getBlogEntries().add(blogEntry);
                })
                .fetchOne();
    }

    public List<Integer> getIds() {
        return nql
                .from(t__)
                .select(t__.id)
                .fetch();
    }

    public List<BlogEntryModel> getBlogs(int count) {
        return eql
                .from(t__)
                .where(c -> c.lt(t__.id, 4000))
                .fetch();
    }

}
