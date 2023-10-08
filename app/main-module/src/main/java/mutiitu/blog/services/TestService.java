package mutiitu.blog.services;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.mutiitu.dao.BlogEntryDao;
import com.mutiitu.domain.BlogEntryModel;
//import com.mutiitu.persistence.SQLiteDB;

import jakarta.transaction.Transactional;

public class TestService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    // @Inject
    // SQLiteDB SQLiteDB;

    @Inject
    BlogEntryDao BlogEntryDao;

    public BlogEntryModel TestA() {
        // create database
        // var migrate = new MigrateDatabaseImpl();
        // migrate.create();
        //
        // var authorCrud = new
        // PersistenceFactory<AuthorModel>().create(AuthorModel.class);
        // var author = new AuthorModel();
        // author.setSurname("Pepe");
        // author.setName("Pepona");
        // authorCrud.insert(author);
        //
        // var blogEntryCrud = new
        // PersistenceFactory<BlogEntryModel>().create(BlogEntryModel.class);
        // var blogEntry = new BlogEntryModel();
        // blogEntry.setTitle("title");
        // blogEntry.setSubtitle("title");
        // blogEntry.setAuthorId(1);
        // blogEntryCrud.insert(blogEntry);
        //
        // var hh = authorCrud.getById(1);
        // System.out.println(hh);
        //
        // var blog = blogEntryCrud.getById(1);
        // System.out.println("---------------------");
        // System.out.println(blog.getAuthor());

        // var author2 = new AuthorDao();
        // var author = new AuthorModel();
        // author.setName("de 19");
        // author.setSurname("de 19");
        // author2.insert(author);

        // var tx = SQLiteDB.getTransactionManager();
        try {
            // tx.begin();

            // var mm = new BlogEntryModel();
            // mm.setAuthorId(1);
            // mm.setTitle("jkjgfl");
            // mm.setSubtitle("jkjgfl");
            // BlogEntryDao.insert(mm);

            // var result = tx.required(
            // () -> {
            // return BlogEntryDao.getWithAuthorsById(2);
            // });

            var result = BlogEntryDao.getWithAuthorsById(2);

            System.out.println("---------------------");
            System.out.println(result.getTitle());
            System.out.println(result.getAuthorId());

            return result;

            // var blogEntity = blogDao2.getWithAuthorsById(2);

            // System.out.println("---------------------");
            // System.out.println(blogEntity.getTitle());
            // System.out.println(blogEntity.getAuthorId());

        } catch (Exception ex) {
            logger.error(null, ex);
            throw ex;
        } finally {
            // tx.getTransaction().rollback();

        }

    }
}
