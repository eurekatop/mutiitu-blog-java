package mutiitu.blog.services.cms;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.mutiitu.dao.cms.CMSEntryDao;
import com.mutiitu.dao.cms.TestJsonEntryDao;
import com.mutiitu.domain.cms.CmsEntryModel;
import com.mutiitu.domain.cms.TestJsonEntryModel;

//TODO: this is a repository
public class TestJsonEntryService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    TestJsonEntryDao testJsonEntryDao;

    public TestJsonEntryModel GetBydId(int id) {
        return testJsonEntryDao.getById(id);
    }
    //
    // public List<Integer> GetAllBlogIds() {
    // return BlogEntryDao.getIds();
    // }
    //
    // public List<BlogEntryModel> GetBlogs(int count) {
    // return BlogEntryDao.getBlogs(count);
    // }

    public void Add(TestJsonEntryModel model) {
        testJsonEntryDao.insert(model);
    }
}
