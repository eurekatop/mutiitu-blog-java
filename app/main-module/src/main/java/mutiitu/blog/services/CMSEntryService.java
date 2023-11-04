package mutiitu.blog.services;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.mutiitu.dao.BlogEntryDao;
import com.mutiitu.dao.cms.CMSEntryDao;
import com.mutiitu.domain.BlogEntryModel;
import com.mutiitu.domain.cms.CmsEntryModel;

//TODO: this is a repository
public class CMSEntryService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    CMSEntryDao CmsEntryDao;

    public CmsEntryModel GetBydId(int id) {
        return CmsEntryDao.getById(id);
    }
    //
    // public List<Integer> GetAllBlogIds() {
    // return BlogEntryDao.getIds();
    // }
    //
    // public List<BlogEntryModel> GetBlogs(int count) {
    // return BlogEntryDao.getBlogs(count);
    // }

    public void Add(CmsEntryModel model) {
        CmsEntryDao.insert(model);
    }

}
