package mutiitu.blog.services;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.mutiitu.dao.AuthorDao;
import com.mutiitu.domain.AuthorModel;

//TODO: this is a repository
public class AuthorService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    AuthorDao AuthorDao;

    public AuthorModel GetBydId(int id) {
        return AuthorDao.getById(id);
    }
    //
    // public List<Integer> GetAllBlogIds() {
    // return BlogEntryDao.getIds();
    // }
    //
    // public List<BlogEntryModel> GetBlogs(int count) {
    // return BlogEntryDao.getBlogs(count);
    // }

    public void Add(AuthorModel model) {
        AuthorDao.insert(model);
    }
}