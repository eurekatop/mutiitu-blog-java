package mutiitu.blog.services;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.mutiitu.dao.BlogEntryDao;
import com.mutiitu.domain.BlogEntryModel;

import mutiitu.blog.models.dto.BlogEntryInputDto;

public class BlogEntryService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    BlogEntryDao BlogEntryDao;

    public BlogEntryModel GetBydId(int id) {
        return BlogEntryDao.getWithAuthorsById(id);
    }

    public List<Integer> GetAllBlogIds() {
        return BlogEntryDao.getIds();
    }

    public List<BlogEntryModel> GetBlogs(int count) {
        return BlogEntryDao.getBlogs(count);
    }

    public void AddBlog(BlogEntryModel blogEntryModel) {
        BlogEntryDao.insert(blogEntryModel);
    }

}
